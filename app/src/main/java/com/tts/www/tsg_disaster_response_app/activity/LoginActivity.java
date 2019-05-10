package com.tts.www.tsg_disaster_response_app.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.singleton.VolleySingleton;
import com.tts.www.tsg_disaster_response_app.util.SHAEncryption;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity {

    EditText userName,password;
    LinearLayout login,otpLogin,qrLogin,signup;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.ed_username);
        password = findViewById(R.id.ed_password);
        login = findViewById(R.id.login_submit);
        otpLogin = findViewById(R.id.otp_login);
        qrLogin = findViewById(R.id.qr_login);
        signup = findViewById(R.id.ll_signUp);

        login.setOnClickListener(this);
        otpLogin.setOnClickListener(this);
        qrLogin.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_submit:
                if(isValid()){
                    if(IsNetworkAvailable()){
                        Login();
                    }
                }
                /*Intent intent = new Intent(LoginActivity.this,MainPage.class);
                startActivity(intent);*/
                break;
            case R.id.otp_login:
                Toasty.info(this, "This page is Under Developement",Toast.LENGTH_SHORT,true).show();
                break;
            case R.id.qr_login:
                Toasty.info(this, "This Page is Under Developement",Toast.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_signUp:
                Toasty.info(this, "This page is Under Developement",Toast.LENGTH_SHORT,true).show();
        }

    }

    public void Login() {
        String url = "http://devwebapi.tatadisasterresponse.com/api/user-login";
        Log.d("!!!url", url);
        showProgressDailog();
        StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("!!!Response", response);
                        dismissProgressDialog();
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.has("status")) {
                                app.setAccessToken(object.getString("access_token"));
                                app.setToken_type(object.getString("token_type"));
                                app.setExpires_in(object.getString("expires_in"));
                                app.setStatus(object.getString("status"));
                                app.setResponse(object.getString("response"));
                                app.setUser_id(object.getString("USER_ID"));
                                app.setFirstName(object.getString("FIRST_NAME"));
                                app.setInitialLogin(object.getString("INITIAL_LOGIN"));
                                app.setTemp_login(object.getString("TEMP_LOGIN"));
                                app.setPasswordExpires(object.getString("PASSWORD_EXPIRE"));
                                app.setSystemRoleId(object.getString("SYSTEM_ROLE_ID"));
                                app.setIssued(object.getString(".issued"));
                                app.setExpires(object.getString(".expires"));

                                Intent intent = new Intent(LoginActivity.this,MainPage.class);
                                startActivity(intent);
                                LoginActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                Toasty.success(LoginActivity.this, "Login Sucessful", Toast.LENGTH_SHORT, true).show();
                                app.setSession(true);
                            } else {
                                Toasty.error(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT, true).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("!!!Error", error.toString());
                dismissProgressDialog();
                Toasty.error(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT, true).show();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("Content-Type", "application/x-www-form-urlencoded");
                return pars;
            }

            @Override
            protected String getParamsEncoding() {
                return "utf-8";
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("username", userName.getText().toString().trim());
            //    pars.put("password", SHAEncryption.getSHA(password.getText().toString().trim()));
                pars.put("password",password.getText().toString());
                pars.put("grant_type", "password");
            //    Log.i("!!!Password", SHAEncryption.getSHA(password.getText().toString().trim()));
                Log.i("!!!Password", password.getText().toString().trim());
                return pars;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(loginRequest);
    }

        @Override
        public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }


    public boolean isValid(){
        boolean flag = true;
        if (userName.getText().toString().trim().length() == 0) {
            EditTextFocus(userName);
            userName.setError("Please enter username");
            flag = false;
        } else if (password.getText().toString().trim().length() == 0) {
            EditTextFocus(password);
            password.setError("Please enter password");
            flag = false;
        }
        return flag;
    }
}
