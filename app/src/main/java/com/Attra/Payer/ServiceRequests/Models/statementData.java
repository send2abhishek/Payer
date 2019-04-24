package com.Attra.Payer.ServiceRequests.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class statementData implements Parcelable {

    private String agentname;
    private String merchant;
    private String merhcnatName;
    private String Time;
    private String date;
    private String TransactionId;
    public double Amount;
    private String Type;
    private String Status;

    public statementData(String agentname, String merchant, String merhcnatName, String time,
                         String date, String transactionId, double amount, String type, String status) {
        this.agentname = agentname;
        this.merchant = merchant;
        this.merhcnatName = merhcnatName;
        Time = time;
        this.date = date;
        TransactionId = transactionId;
        Amount = amount;
        Type = type;
        Status = status;
    }


    protected statementData(Parcel in) {
        agentname = in.readString();
        merchant = in.readString();
        merhcnatName = in.readString();
        Time = in.readString();
        date = in.readString();
        TransactionId = in.readString();
        Amount = in.readDouble();
        Type = in.readString();
        Status = in.readString();
    }

    public static final Creator<statementData> CREATOR = new Creator<statementData>() {
        @Override
        public statementData createFromParcel(Parcel in) {
            return new statementData(in);
        }

        @Override
        public statementData[] newArray(int size) {
            return new statementData[size];
        }
    };

    public String getAgentname() {
        return agentname;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getMerhcnatName() {
        return merhcnatName;
    }

    public void setMerhcnatName(String merhcnatName) {
        this.merhcnatName = merhcnatName;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public double getAmount() {
        return Amount;
    }

    public String getType() {
        return Type;
    }

    public String getStatus() {
        return Status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(agentname);
        dest.writeString(merchant);
        dest.writeString(merhcnatName);
        dest.writeString(Time);
        dest.writeString(date);
        dest.writeString(TransactionId);
        dest.writeDouble(Amount);
        dest.writeString(Type);
        dest.writeString(Status);
    }
}
