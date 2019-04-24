package com.Attra.Payer.ServiceRequests.Models;

public class AccountData {


    private String account_type;
    private int accountLimit;
    private int transactionLimit;
    private String frequency;

    public AccountData(String account_type, int accountLimit, int transactionLimit, String frequency) {
        this.account_type = account_type;
        this.accountLimit = accountLimit;
        this.transactionLimit = transactionLimit;
        this.frequency = frequency;
    }

    public String getAccount_type() {
        return account_type;
    }

    public int getAccountLimit() {
        return accountLimit;
    }

    public int getTransactionLimit() {
        return transactionLimit;
    }

    public String getFrequency() {
        return frequency;
    }
}
