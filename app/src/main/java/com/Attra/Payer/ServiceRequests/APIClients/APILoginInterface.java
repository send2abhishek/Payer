package com.Attra.Payer.ServiceRequests.APIClients;


import com.Attra.Payer.ServiceRequests.Models.LoginUser;
import com.Attra.Payer.ServiceRequests.Models.scanToPay;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

public interface APILoginInterface {


    @POST("login?include=user")
     Call<ResponseBody> loginUser(@Body LoginUser user);



    @GET("accounts/{id}")
    Call<ResponseBody> getAccountDetails(@Path("id") String id);


    @GET("getTransactionForPayer")
    Call<ResponseBody> getPayerAccountDetails(@Query("mobileNumber") String mobileNumber);



    @POST("scanToPay")
    Call<ResponseBody> payment(@Body scanToPay pay);

}


