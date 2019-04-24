package com.Attra.Payer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.Attra.Payer.Models.ScanResponse;
import com.Attra.Payer.ServiceRequests.Models.AcntDetails;
import com.Attra.Payer.Services.AccountService;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.otto.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ScannerActivity extends BaseActivity{

    private String scanContent;
    private String scanFormat;
    private boolean isScanningResult=false;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Validating Merchant");
        progressDialog.setCancelable(false);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CaptureActivityAnyOrientation.class);
        integrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
            if (scanningResult.getContents() != null) {
                scanContent = scanningResult.getContents().toString();
                scanFormat = scanningResult.getFormatName().toString();
                isScanningResult=true;
            }

            else {
                finish();
            }

            try{
                Gson gson=new Gson();
                ScanResponse response=gson.fromJson(scanContent,ScanResponse.class);

                if(response.getAccountId()!=null){

                    String mrchMobileId = response.getAccountId();
                    String mrchMobileNbr = mrchMobileId.substring(2);
                    ValidateMerchant(mrchMobileNbr);
                }

                else {
                    Toast.makeText(this,"Invalid QR Code Format",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            catch (Exception e){

                if(isScanningResult){
                    Toast.makeText(this,"Invalid QR Code Format",Toast.LENGTH_LONG).show();
                }
                finish();
            }





            //textView.setText(scanContent + "    type:" + scanFormat);

        } else {
            Toast.makeText(this, "Nothing scanned", Toast.LENGTH_SHORT).show();
        }
    }


    private void ValidateMerchant(String mrchMobileNbr) {

        //Toast.makeText(this,mrchMobileNbr,Toast.LENGTH_LONG).show();
        progressDialog.show();
        bus.post(new AccountService.MerchantInquiryRequest("77"+mrchMobileNbr,progressDialog));
    }




    @Subscribe
    public void onMerchantInquiryResponse(AccountService.MerchantInquiryResponse response){

        if(!response.didSucceed()){

            Toast.makeText(this,response.getPropertyErrors("MobileNumberError"),Toast.LENGTH_LONG).show();

        }
        else {

            if(response.MerchantAccountResponse.isSuccessful()){

                try {
                    JSONObject res = new JSONObject(response.MerchantAccountResponse.body().string());
                    Gson gson=new Gson();
                    AcntDetails details=gson.fromJson(res.toString(),AcntDetails.class);
                    if(details.getName()!=null){
                        navigateToPaymentPage(details.getName(),details.getAccountId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();
            }
        }
    }

    private void navigateToPaymentPage(String name, String accountId) {

        Intent intent=new Intent(this,PaymentPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PaymentPageActivity.MERCHANT_NAME,name);
        intent.putExtra(PaymentPageActivity.MERCHANT_MOBILE_NBR,accountId.substring(2));
        startActivity(intent);
        finish();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
