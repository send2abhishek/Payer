package com.Attra.Payer.ServiceRequests.Models;

import java.util.ArrayList;

public class txnHistory {

    private String responseCode;
    private ArrayList<statementData> statementData;

    public txnHistory(String responseCode, ArrayList<com.Attra.Payer.ServiceRequests.Models.statementData> statementData) {
        this.responseCode = responseCode;
        this.statementData = statementData;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public ArrayList<com.Attra.Payer.ServiceRequests.Models.statementData> getStatementData() {
        return statementData;
    }
}
