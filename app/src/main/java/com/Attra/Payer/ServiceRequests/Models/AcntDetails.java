package com.Attra.Payer.ServiceRequests.Models;

public class AcntDetails {


    private String accountId;
    private String _id;
    private String accountType;
    private double balance;
    private String qrCode;
    private String name;
    private String currencyType;
    private String accountAddedDateAndTime;
    private AccountData Account;

    public AcntDetails(String accountId, String _id, String accountType, double balance, String qrCode, String name,
                       String currencyType,
                       String accountAddedDateAndTime, AccountData account) {
        this.accountId = accountId;
        this._id = _id;
        this.accountType = accountType;
        this.balance = balance;
        this.qrCode = qrCode;
        this.name = name;
        this.currencyType = currencyType;
        this.accountAddedDateAndTime = accountAddedDateAndTime;
        Account = account;
    }

    public String getAccountId() {
        return accountId;
    }

    public String get_id() {
        return _id;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getName() {
        return name;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getAccountAddedDateAndTime() {
        return accountAddedDateAndTime;
    }

    public AccountData getAccount() {
        return Account;
    }
}
