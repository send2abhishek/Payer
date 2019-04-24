package com.Attra.Payer.ServiceRequests;

import android.widget.Toast;
import com.Attra.Payer.Infrastructure.PayerApp;
import com.Attra.Payer.ServiceRequests.APIClients.APIClientLogin;
import com.Attra.Payer.ServiceRequests.APIClients.APILoginInterface;
import com.Attra.Payer.ServiceRequests.Models.LoginUser;
import com.Attra.Payer.ServiceRequests.Models.scanToPay;
import com.Attra.Payer.Services.AccountService;
import com.squareup.otto.Subscribe;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveAccountService extends BaseServiceRequest {

    private APILoginInterface apiLoginInterface;

    public LiveAccountService(PayerApp application) {
        super(application);
    }


    @Subscribe
    public void LoginRequest(final AccountService.LoginRequestRequest request){

        final AccountService.LoginRequestResponse loginresponse=new AccountService.LoginRequestResponse();

        if(request.username.isEmpty() || request.username.length() < 10){

            loginresponse.setPropertyErrors("UserNameError","Invalid Username");
        }


        else if(request.Password.isEmpty()){

            loginresponse.setPropertyErrors("PasswordError","Invalid Password");
        }




        else {


            if(loginresponse.didSucceed()){

                request.progressDialog.show();

                apiLoginInterface = APIClientLogin.getClient().create(APILoginInterface.class);
                Call<ResponseBody> serviceCall=apiLoginInterface.loginUser(new LoginUser(request.username,request.Password));

                serviceCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                        loginresponse.response = response;

                        bus.post(loginresponse);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        request.progressDialog.dismiss();
                        Toast.makeText(application.getApplicationContext(),"Something Went Wrong", Toast.LENGTH_LONG).show();
                    }
                });





            }



        }


       if(loginresponse.getpropertySize() > 0){
            bus.post(loginresponse);
        }




    }

    @Subscribe
    public void onAccountInquiryRequest(AccountService.AccountInquiryRequest request){

        final AccountService.AccountInquiryResponse responseCall=new AccountService.AccountInquiryResponse();

        if(request.mobileNbr.isEmpty()){
            responseCall.setPropertyErrors("MobileNumberError","Mobile Number Invalid");
        }

        else {

            if(responseCall.didSucceed()){

                apiLoginInterface=null;
                apiLoginInterface=APIClientLogin.getClientAccountInquiry().create(APILoginInterface.class);
                Call<ResponseBody> accoutDetails=apiLoginInterface.getAccountDetails(request.mobileNbr);
                accoutDetails.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        responseCall.AccountResponse=response;

                        bus.post(responseCall);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(application.getApplicationContext(),"Failed to fetch account balance", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }



    @Subscribe
    public void onAccountHistory(AccountService.PayerInquiryRequest request){

        final AccountService.PayerInquiryResponse payerInquiryResponse=new AccountService.PayerInquiryResponse();

        if(payerInquiryResponse.didSucceed()){

            apiLoginInterface=null;
            apiLoginInterface=APIClientLogin.getClientPayerAccountInquiry().create(APILoginInterface.class);
            Call<ResponseBody> PayeraccoutDetails=apiLoginInterface.getPayerAccountDetails(request.mobileNbr);
            PayeraccoutDetails.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    payerInquiryResponse.PayerAccountResponse=response;
                    bus.post(payerInquiryResponse);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(application.getApplicationContext(),"Failed to fetch account balance", Toast.LENGTH_LONG).show();
                }
            });

        }
    }



    @Subscribe
    public void onMerchantInquiryRequest(final AccountService.MerchantInquiryRequest request){

        final AccountService.MerchantInquiryResponse responseCall=new AccountService.MerchantInquiryResponse();

        if(request.mobileNumber.isEmpty()){
            responseCall.setPropertyErrors("MobileNumberError","Mobile Number Invalid");
            request.dialog.dismiss();
        }

        else {

            if(responseCall.didSucceed()){

                apiLoginInterface=null;
                apiLoginInterface=APIClientLogin.getClientAccountInquiry().create(APILoginInterface.class);
                Call<ResponseBody> accoutDetails=apiLoginInterface.getAccountDetails(request.mobileNumber);
                accoutDetails.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        responseCall.MerchantAccountResponse=response;

                        bus.post(responseCall);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(application.getApplicationContext(),"Failed to fetch Merchant Details", Toast.LENGTH_LONG).show();
                        request.dialog.dismiss();
                    }
                });
            }
        }
    }

    @Subscribe
    public void onPaymentResponse(final AccountService.PaymentRequest request){

        final AccountService.PaymentResponse paymentResponse=new AccountService.PaymentResponse();
        double amount;
        try {
            amount = new Double(request.payAmnt);
        } catch (NumberFormatException e) {
            amount = 0; // your default value
            paymentResponse.setPropertyErrors("AmountEmpty","Amount Can't be Empty");
        }

        if(request.payAmnt.isEmpty()){

            paymentResponse.setPropertyErrors("AmountEmpty","Amount Can't be Empty");

        }

        else if(amount < 1){
            paymentResponse.setPropertyErrors("InvalidAmount","Amount Can't be less than Rs 1");

        }

        else {

            if(paymentResponse.didSucceed()){
                request.dialog.show();
                apiLoginInterface=null;
                apiLoginInterface=APIClientLogin.getClientPayerAccountInquiry().create(APILoginInterface.class);
                Call<ResponseBody> responseCall=apiLoginInterface
                        .payment(new scanToPay(request.PayerMobileNbr,request.MerMobileNbr,
                                amount,request.PayerName,request.merchntName));
                responseCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        paymentResponse.PaymentResponse=response;
                        bus.post(paymentResponse);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(application.getApplicationContext(),"Payment Failed, Try Again !", Toast.LENGTH_LONG).show();
                        request.dialog.dismiss();
                    }
                });

            }
        }

        if( paymentResponse.getpropertySize() > 0){
            bus.post(paymentResponse);
        }
    }

}
