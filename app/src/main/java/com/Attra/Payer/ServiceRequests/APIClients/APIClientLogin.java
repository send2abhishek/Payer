package com.Attra.Payer.ServiceRequests.APIClients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientLogin {

    public static final String BASE_URL = "http://ec2-13-127-1-106.ap-south-1.compute.amazonaws.com:4000/api/payers/";
    private static Retrofit retrofit = null;
    private static String BASE_URL_ACCOUNT_INQUIRY="http://ec2-13-127-1-106.ap-south-1.compute.amazonaws.com:4001/api/";
    private static String BASE_URL_PAYER_HISTORY_INQUIRY="http://ec2-13-127-1-106.ap-south-1.compute.amazonaws.com:4003/api/Transactions/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientAccountInquiry() {





            Retrofit retrofit1 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_ACCOUNT_INQUIRY)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit1;
    }


    public static Retrofit getClientPayerAccountInquiry() {


        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(BASE_URL_PAYER_HISTORY_INQUIRY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit2;
    }



}
