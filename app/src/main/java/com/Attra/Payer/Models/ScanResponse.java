package com.Attra.Payer.Models;

public class ScanResponse {

    private String accountId;
    private String accountType;
    private String name;

    public ScanResponse(String accountId, String accountType, String name) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }
}
