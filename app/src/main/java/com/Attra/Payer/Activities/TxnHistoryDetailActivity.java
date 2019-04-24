package com.Attra.Payer.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.statementData;

public class TxnHistoryDetailActivity extends BaseActivity {

    private TextView merchantNameheader;
    private TextView amountPaid;
    private TextView date;
    private TextView time;
    private TextView merchantHistStatus;
    private TextView merchantName;
    private TextView merchantMobile;
    private TextView merchantTxnId;
    private TextView merchantTxnType;
    public static final String TXN_HIST_DETAILS="TXN_HIST_DETAILS";
    private statementData data;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txn_hist_detail);
        toolbar=(Toolbar)findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Transaction Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        merchantNameheader=(TextView)findViewById(R.id.activity_txn_det_mer_amount_paid_title);
        amountPaid=(TextView)findViewById(R.id.activity_txn_det_amount);
        date=(TextView)findViewById(R.id.activity_txn_det_date);
        time=(TextView)findViewById(R.id.activity_txn_det_time);
        merchantHistStatus=(TextView)findViewById(R.id.activity_txn_det_amount_paid);
        merchantName=(TextView)findViewById(R.id.activity_txn_det_mer_name);
        merchantMobile=(TextView)findViewById(R.id.activity_txn_det_mer_mobile);
        merchantTxnId=(TextView)findViewById(R.id.activity_txn_det_txn_id_det);
        merchantTxnType=(TextView)findViewById(R.id.activity_txn_det_txn_type_det);
        data=getIntent().getParcelableExtra(TXN_HIST_DETAILS);

        if(data!=null){
            merchantNameheader.setText(data.getMerhcnatName());
            amountPaid.setText(Double.toString(data.getAmount()));
            date.setText(data.getDate());
            time.setText(data.getTime());
            merchantName.setText(data.getMerhcnatName());
            merchantTxnId.setText(data.getTransactionId());
            merchantTxnType.setText(data.getType());
            merchantMobile.setText(data.getMerchant());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
