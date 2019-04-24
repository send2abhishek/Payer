package com.Attra.Payer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.AcntDetails;
import com.Attra.Payer.ServiceRequests.Models.LoginResponse;
import com.Attra.Payer.Services.AccountService;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HomePageActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private LoginResponse loginResponse;
    private TextView profileName;
    private TextView profileBal;
    public static String profileMobile;
    private LinearLayout txnHistory;
    private LinearLayout ScanToPay;
    public static String payerBalnce;
    public static String payerName;
    public static String payerMobileNbr;
    public static int PayerAcctLimit;
    public static int PayerTxnLimit;
    public static final String SEND_RESPONSE="SEND_RESPONSE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setUpDrawerToolbar();
        loginResponse=getIntent().getParcelableExtra(SEND_RESPONSE);
        profileName=(TextView)findViewById(R.id.activity_main_profile_name);
        profileBal=(TextView)findViewById(R.id.activity_main_profile_bal);
        txnHistory=(LinearLayout)findViewById(R.id.activity_home_txn_history_layout);
        ScanToPay=(LinearLayout)findViewById(R.id.activity_linear_layout);
        ScanToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePageActivity.this,ScannerActivity.class);
                startActivity(intent);
            }
        });
        txnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomePageActivity.this,TxnHistoryActivity.class);
                startActivity(intent);
            }
        });

        if(loginResponse !=null){
            payerName=loginResponse.getUser().getFirstName()+ " " + loginResponse.getUser().getLastName();
            payerMobileNbr=loginResponse.getUser().getMobile();
            profileName.setText(loginResponse.getUser().getFirstName());
            profileMobile=loginResponse.getUser().getMobile();
            bus.post(new AccountService.AccountInquiryRequest("80"+loginResponse.getUser().getMobile()));
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bus.post(new AccountService.AccountInquiryRequest("80"+loginResponse.getUser().getMobile()));
    }

    @Subscribe
    public void onAccountInquiryResponse(AccountService.AccountInquiryResponse response){

        if(!response.didSucceed()){

            Toast.makeText(this,response.getPropertyErrors("MobileNumberError"),Toast.LENGTH_LONG).show();
        }
        else {

            if(response.AccountResponse.isSuccessful()){

                try {
                    JSONObject res = new JSONObject(response.AccountResponse.body().string());
                    Gson gson=new Gson();
                    AcntDetails details=gson.fromJson(res.toString(),AcntDetails.class);
                    PayerTxnLimit=details.getAccount().getTransactionLimit();
                    PayerAcctLimit=details.getAccount().getAccountLimit();
                    payerBalnce=Double.toString(details.getBalance());
                    profileBal.setText(payerBalnce);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void setUpDrawerToolbar() {
        drawerLayout=(DrawerLayout)findViewById(R.id.activity_home_drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_hamberger);
        toolbar.setLogo(R.mipmap.ic_logo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
        TextView textView=(TextView)findViewById(R.id.drawer_account_det);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePageActivity.this,AccountDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;

    }



}
