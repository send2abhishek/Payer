package com.Attra.Payer.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.TransHistResponse;
import com.Attra.Payer.ServiceRequests.Models.statementData;
import com.Attra.Payer.Services.AccountService;
import com.Attra.Payer.Views.AccdtAdapter;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import static com.Attra.Payer.Activities.HomePageActivity.profileMobile;


public class AccountDetailsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TextView PayerName;
    private TextView PayerNbr;
    private TextView PayertxnLimit;
    private TextView PayerActLimit;
    private TextView PayerActBal;
    private Toolbar toolbar;
    private AccdtAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        setUpViews();
        adapter=new AccdtAdapter(this);
        bus.post(new AccountService.PayerInquiryRequest(profileMobile));


    }


    @Subscribe
    public void onPayerInquiryResponse(AccountService.PayerInquiryResponse response){
        if(response.PayerAccountResponse.isSuccessful()){

            try {
                JSONObject res = new JSONObject(response.PayerAccountResponse.body().string());
                Gson gson=new Gson();
                TransHistResponse transHistResponse=gson.fromJson(res.toString(),TransHistResponse.class);

                for (statementData data:transHistResponse.getResponse().getStatementData()) {

                    //data.setAmount(Double.parseDouble("-₹"+data.getAmount()));

                    if(data.getType().equals("Pay")){
                        data.setMerhcnatName( "Paid To: " + data.getMerhcnatName());
                    }
                    else {
                        data.setMerhcnatName( "Funds Loaded ");
                    }
                    adapter.AddDataToView(data);
                }
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));





            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUpViews() {
        toolbar=(Toolbar)findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Account Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=(RecyclerView)findViewById(R.id.activity_account_recylerview);
        PayertxnLimit=(TextView)findViewById(R.id.activity_account_txn_limit_amount);
        PayerName=(TextView)findViewById(R.id.activity_account_profile_name);
        PayerNbr=(TextView)findViewById(R.id.activity_account_profile_number);
        PayerActLimit=(TextView)findViewById(R.id.activity_account_limit_amount);
        PayerActBal=(TextView)findViewById(R.id.activity_account_bal);
        //PayertxnLimit.setText(HomePageActivity.PayerTxnLimit);
       // PayerActLimit.setText(HomePageActivity.PayerAcctLimit);
        PayerName.setText(HomePageActivity.payerName);
        PayerNbr.setText(HomePageActivity.payerMobileNbr);
        PayerActBal.setText(HomePageActivity.payerBalnce);
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
