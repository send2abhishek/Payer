package com.Attra.Payer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.statementData;
import com.Attra.Payer.ServiceRequests.Models.TransHistResponse;
import com.Attra.Payer.Services.AccountService;
import com.Attra.Payer.Views.MessageAdapter;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

import static com.Attra.Payer.Activities.HomePageActivity.profileMobile;

public class TxnHistoryActivity extends BaseActivity implements MessageAdapter.onMessageClickListener {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<statementData> statementData;
    private MessageAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txn_history);
        setUpDrawerToolbar();
        bus.post(new AccountService.PayerInquiryRequest(profileMobile));
        adapter=new MessageAdapter(this,this);
        recyclerView=(RecyclerView)findViewById(R.id.activity_txn_history_recylerview);

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

    private void setUpDrawerToolbar() {
        drawerLayout=(DrawerLayout)findViewById(R.id.activity_home_drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_hamberger);
        //toolbar.setLogo(R.mipmap.ic_logo);
        getSupportActionBar().setTitle("Transaction History");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drawerLayout.isDrawerOpen(Gravity.START)){

                    drawerLayout.closeDrawer(Gravity.START);
                }

                else {

                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
    }

    @Override
    public void OnmessageClicked(statementData data) {

        Intent intent=new Intent(this,TxnHistoryDetailActivity.class);
        intent.putExtra(TxnHistoryDetailActivity.TXN_HIST_DETAILS,data);
        startActivity(intent);
    }
}
