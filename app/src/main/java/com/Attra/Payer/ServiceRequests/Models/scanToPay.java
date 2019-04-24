package com.Attra.Payer.ServiceRequests.Models;

public class scanToPay {

    private String debitAccount;
    private String creditAccount;
    private double  amount ;
    private String  payerName;
    private String merchantName;

    public scanToPay(String debitAccount, String creditAccount, double amount, String payerName, String merchantName) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.payerName = payerName;
        this.merchantName = merchantName;
    }
}
