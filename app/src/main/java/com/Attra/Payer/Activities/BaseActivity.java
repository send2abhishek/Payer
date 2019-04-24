package com.Attra.Payer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.Attra.Payer.Infrastructure.PayerApp;
import com.Attra.Payer.R;
import com.squareup.otto.Bus;

public class BaseActivity extends AppCompatActivity {


    protected Bus bus;
    protected PayerApp application;
    private Boolean isRegisteredwithBus=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application=(PayerApp)getApplication();
        bus=application.getBus();
        bus.register(this);
        isRegisteredwithBus=true;
    }


    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(isRegisteredwithBus){
            bus.unregister(this);
            isRegisteredwithBus=false;
        }
    }


    @Override
    public void finish(){
        super.finish();
        if(isRegisteredwithBus){
            bus.unregister(this);
            isRegisteredwithBus=false;
        }

    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }
}
