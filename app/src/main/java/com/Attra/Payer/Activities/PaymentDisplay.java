package com.Attra.Payer.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.ScanToPayResponse;

public class PaymentDisplay extends BaseActivity {

    private TextView merchantName;
    private TextView merchantNnumber;
    private TextView TxnId;
    private TextView TxnType;
    private TextView TxnDate;
    private TextView TxnTime;
    private TextView TxnAmount;
    public static final String PAYMENT_RESPONSE="PAYMENT_RESPONSE";
    public static final String PAYMENT_RESPONSE_MERCHANT_NAME="PAYMENT_RESPONSE_MERCHANT_NAME";
    public static final String PAYMENT_RESPONSE_MERCHANT_NBR="PAYMENT_RESPONSE_MERCHANT_NBR";
    public static final String PAYMENT_RESPONSE_MERCHANT_AMOUNT="PAYMENT_RESPONSE_MERCHANT_AMOUNT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);
        merchantName=(TextView)findViewById(R.id.activity_payment_result_mer_name);
        merchantNnumber=(TextView)findViewById(R.id.activity_payment_result_mer_mob_nbr);
        TxnId=(TextView)findViewById(R.id.activity_payment_result_txn_id_det);
        TxnType=(TextView)findViewById(R.id.activity_payment_result_txn_type_det);
        TxnDate=(TextView)findViewById(R.id.activity_txn_det_date);
        TxnTime=(TextView)findViewById(R.id.activity_txn_det_time);
        TxnAmount=(TextView)findViewById(R.id.activity_payment_result_amount);


        ScanToPayResponse response=getIntent().getParcelableExtra(PAYMENT_RESPONSE);
        TxnId.setText(response.getResponse().getTransactionId());
        TxnType.setText(response.getResponse().getTransactionType());
        TxnDate.setText(response.getResponse().getDate());
        TxnTime.setText(response.getResponse().getTime());
        merchantName.setText(getIntent().getStringExtra(PAYMENT_RESPONSE_MERCHANT_NAME));
        merchantNnumber.setText(getIntent().getStringExtra(PAYMENT_RESPONSE_MERCHANT_NBR));
        TxnAmount.setText(getIntent().getStringExtra(PAYMENT_RESPONSE_MERCHANT_AMOUNT));
    }
}
