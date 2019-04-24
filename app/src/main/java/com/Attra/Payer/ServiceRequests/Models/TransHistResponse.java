package com.Attra.Payer.ServiceRequests.Models;



public class TransHistResponse {


    private txnHistory response;

    public TransHistResponse(txnHistory response) {
        this.response = response;
    }

    public txnHistory getResponse() {
        return response;
    }
}