package com.Attra.Payer.ServiceRequests.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ScanToPayResponse implements Parcelable {

    private PaymentResult response;

    public ScanToPayResponse(PaymentResult response) {
        this.response = response;
    }

    protected ScanToPayResponse(Parcel in) {
        response = in.readParcelable(PaymentResult.class.getClassLoader());
    }

    public static final Creator<ScanToPayResponse> CREATOR = new Creator<ScanToPayResponse>() {
        @Override
        public ScanToPayResponse createFromParcel(Parcel in) {
            return new ScanToPayResponse(in);
        }

        @Override
        public ScanToPayResponse[] newArray(int size) {
            return new ScanToPayResponse[size];
        }
    };

    public PaymentResult getResponse() {
        return response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(response, flags);
    }

    public static class PaymentResult implements Parcelable {

        private int responseCode;
        private String TransactionType;
        private String TransactionId;
        private String date;
        private String time;
        private String message;


        public PaymentResult(int responseCode, String transactionType, String transactionId,
                             String date, String time, String message) {
            this.responseCode = responseCode;
            TransactionType = transactionType;
            TransactionId = transactionId;
            this.date = date;
            this.time = time;
            this.message = message;
        }

        protected PaymentResult(Parcel in) {
            responseCode = in.readInt();
            TransactionType = in.readString();
            TransactionId = in.readString();
            date = in.readString();
            time = in.readString();
            message = in.readString();
        }

        public static final Creator<PaymentResult> CREATOR = new Creator<PaymentResult>() {
            @Override
            public PaymentResult createFromParcel(Parcel in) {
                return new PaymentResult(in);
            }

            @Override
            public PaymentResult[] newArray(int size) {
                return new PaymentResult[size];
            }
        };

        public int getResponseCode() {
            return responseCode;
        }

        public String getTransactionType() {
            return TransactionType;
        }

        public String getTransactionId() {
            return TransactionId;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(responseCode);
            dest.writeString(TransactionType);
            dest.writeString(TransactionId);
            dest.writeString(date);
            dest.writeString(time);
            dest.writeString(message);
        }
    }
}




