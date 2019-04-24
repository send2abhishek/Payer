package com.Attra.Payer.Services;
import android.app.ProgressDialog;
import com.Attra.Payer.Infrastructure.ServiceResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AccountService {


    private AccountService() {
    }


    public static class LoginRequestRequest {

        public String username;
        public String Password;
        public ProgressDialog progressDialog;

        public LoginRequestRequest(String username, String password, ProgressDialog progressDialog) {
            this.username = username;
            Password = password;
            this.progressDialog = progressDialog;
        }
    }


    public static class LoginRequestResponse extends ServiceResponse {

        public Response<ResponseBody> response;

    }

    public static class AccountInquiryRequest{

        public String mobileNbr;

        public AccountInquiryRequest(String mobileNbr) {
            this.mobileNbr = mobileNbr;
        }
    }

    public static class AccountInquiryResponse extends ServiceResponse{
        public Response<ResponseBody> AccountResponse;
    }

    public static class PayerInquiryRequest{

        public String mobileNbr;

        public PayerInquiryRequest(String mobileNbr) {
            this.mobileNbr = mobileNbr;
        }
    }

    public static class PayerInquiryResponse extends ServiceResponse{
        public Response<ResponseBody> PayerAccountResponse;
    }

    public static class MerchantInquiryRequest{

        public String mobileNumber;
        public ProgressDialog dialog;

        public MerchantInquiryRequest(String mobileNumber, ProgressDialog dialog) {
            this.mobileNumber = mobileNumber;
            this.dialog = dialog;
        }
    }

    public static class MerchantInquiryResponse extends ServiceResponse{
        public Response<ResponseBody> MerchantAccountResponse;
    }


    public static class PaymentRequest{

        public String PayerMobileNbr;
        public String MerMobileNbr;
        public String payAmnt;
        public String PayerName;
        public String merchntName;
        public ProgressDialog dialog;

        public PaymentRequest(String payerMobileNbr, String merMobileNbr, String payAmnt, String payerName,
                              String merchntName, ProgressDialog dialog) {
            PayerMobileNbr = payerMobileNbr;
            MerMobileNbr = merMobileNbr;
            this.payAmnt = payAmnt;
            PayerName = payerName;
            this.merchntName = merchntName;
            this.dialog = dialog;
        }
    }
    public static class PaymentResponse extends ServiceResponse{
        public Response<ResponseBody> PaymentResponse;
    }
}
