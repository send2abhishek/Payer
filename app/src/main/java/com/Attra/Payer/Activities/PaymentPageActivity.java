package com.Attra.Payer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.ScanToPayResponse;
import com.Attra.Payer.Services.AccountService;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PaymentPageActivity extends BaseActivity implements View.OnClickListener {

    public static final String MERCHANT_NAME="MERCHANT_NAME";
    public static final String MERCHANT_MOBILE_NBR="MERCHANT_MOBILE_NBR";
    private TextView merchantNameHolder;
    private TextView merchantNbrHolder;
    private TextView PayerBal;
    private String merchantName;
    private String merchantMobile;
    private Toolbar toolbar;
    private Button payBtn;
    private EditText amountEntry;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);
        merchantName=getIntent().getStringExtra(MERCHANT_NAME);
        merchantMobile=getIntent().getStringExtra(MERCHANT_MOBILE_NBR);
        setUpDrawerToolbar();
        setUpViews();


    }

    private void setUpDrawerToolbar() {

        toolbar=(Toolbar)findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Proceed To Payment");
    }

    private void setUpViews() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Payment In Process");
        progressDialog.setCancelable(false);
        merchantNameHolder=(TextView)findViewById(R.id.activity_payment_mer_name);
        merchantNbrHolder=(TextView)findViewById(R.id.activity_payment_mer_mob_nbr);
        PayerBal=(TextView)findViewById(R.id.activity_payment_avl_bal);
        payBtn=(Button)findViewById(R.id.activity_payment_pay_btn);
        payBtn.setOnClickListener(this);
        amountEntry=(EditText)findViewById(R.id.activity_payment_amount_entry);
        merchantNameHolder.setText(merchantName);
        merchantNbrHolder.setText("+91 "+merchantMobile);
        PayerBal.setText("Available Balance : " + HomePageActivity.payerBalnce);
    }

    @Override
    public void onClick(View v) {


        bus.post(new AccountService.PaymentRequest(HomePageActivity.payerMobileNbr,
                merchantMobile,amountEntry.getText().toString(),HomePageActivity.payerName,merchantName,progressDialog));
    }

    @Subscribe
    public void OnPaymentResponseCall(AccountService.PaymentResponse paymentResponse){

        if(!paymentResponse.didSucceed()){

            amountEntry.setError(paymentResponse.getPropertyErrors("AmountEmpty"));
            amountEntry.setError(paymentResponse.getPropertyErrors("InvalidAmount"));
        }

        else {

           if(paymentResponse.PaymentResponse.isSuccessful()){

               try {
                   JSONObject res = new JSONObject(paymentResponse.PaymentResponse.body().string());

                   Gson gson=new Gson();
                   ScanToPayResponse details=gson.fromJson(res.toString(), ScanToPayResponse.class);

                   progressDialog.dismiss();

                   if(details.getResponse().getResponseCode()==700){

                       Intent intent=new Intent(this,PaymentDisplay.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                               Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.putExtra(PaymentDisplay.PAYMENT_RESPONSE,details);
                       intent.putExtra(PaymentDisplay.PAYMENT_RESPONSE_MERCHANT_NAME,merchantName);
                       intent.putExtra(PaymentDisplay.PAYMENT_RESPONSE_MERCHANT_NBR,merchantMobile);
                       intent.putExtra(PaymentDisplay.PAYMENT_RESPONSE_MERCHANT_AMOUNT,amountEntry.getText().toString());
                       startActivity(intent);
                       finish();
                   }
                   else {
                       Toast.makeText(this,details.getResponse().getMessage(),Toast.LENGTH_LONG).show();
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
           else {
                Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_LONG).show();
           }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
