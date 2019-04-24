package com.Attra.Payer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.Attra.Payer.R;
import com.Attra.Payer.ServiceRequests.Models.ErrorHandle;
import com.Attra.Payer.ServiceRequests.Models.LoginResponse;
import com.Attra.Payer.Services.AccountService;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import static com.Attra.Payer.Activities.HomePageActivity.SEND_RESPONSE;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText username;
    private EditText Password;
    private Button loginBtn;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.activity_login_username_field);
        Password=(EditText)findViewById(R.id.activity_login_password_field);
        loginBtn=(Button)findViewById(R.id.activity_login_btn);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Attempting To Login");
        progressDialog.setCancelable(false);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if(loginBtn !=null){

            bus.post(new AccountService.LoginRequestRequest(username.getText().toString(),Password.getText().toString(),progressDialog));
        }
    }


    @Subscribe
    public void onLoginResponse(AccountService.LoginRequestResponse LoginResponse){


        if(!LoginResponse.didSucceed()){

            username.setError(LoginResponse.getPropertyErrors("UserNameError"));
            Password.setError(LoginResponse.getPropertyErrors("PasswordError"));
        }

        else {

            if(LoginResponse.response.isSuccessful()){

                try {
                    JSONObject res = new JSONObject(LoginResponse.response.body().string());
                    Gson gson=new Gson();
                    LoginResponse loginResponse=gson.fromJson(res.toString(),LoginResponse.class);
                    progressDialog.dismiss();
                    Intent intent=new Intent(this,HomePageActivity.class);
                    intent.putExtra(SEND_RESPONSE,loginResponse);
                    startActivity(intent);
                    finish();


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            else if(LoginResponse.response.raw().code()==401){
                try {
                    JSONObject res = new JSONObject(LoginResponse.response.errorBody().string());


                    Gson gson=new Gson();
                    ErrorHandle errorHandle=gson.fromJson(res.toString(),ErrorHandle.class);
                    progressDialog.dismiss();
                    Toast.makeText(application.getApplicationContext(),errorHandle.getError().getMessage(), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else {
               progressDialog.dismiss();
                try {
                    JSONObject jObjError = new JSONObject(LoginResponse.response.errorBody().string());
                    Toast.makeText(application.getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(application.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }


    }
}
