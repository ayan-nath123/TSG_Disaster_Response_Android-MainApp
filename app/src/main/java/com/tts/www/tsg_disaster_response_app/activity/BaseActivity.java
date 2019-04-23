package com.tts.www.tsg_disaster_response_app.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.application.DRApplication;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public DRApplication app;
    public ProgressDialog prsDlg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DRApplication) getApplication();

        //**************************************//
        prsDlg = new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {

    }

    //**********Internet Connection Checking start *************//
    //Work on Progress
    public boolean IsNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean f = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if(f){
            return true;
        } else {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.no_network);
            dialog.setCancelable(false);
            Button btOpenSetting = (Button) dialog.findViewById(R.id.btOpenSettings);
            Button btCancel = (Button) dialog.findViewById(R.id.btCancel);
            btOpenSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                    dialog.dismiss();
                }
            });
            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return false;
        }
    }
    //*************Internet Connection checking***********//
    public void showProgressDailog() {
        if (!prsDlg.isShowing()) {
            prsDlg.setMessage("Please wait...");
            prsDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prsDlg.setIndeterminate(true);
            prsDlg.setCancelable(false);
            prsDlg.show();
        }

    }

    public void dismissProgressDialog() {
        if (prsDlg.isShowing()) {
            prsDlg.dismiss();
        }
    }

    public void FinishIntent(Class intentClass){
        Intent intent = new Intent(getApplicationContext(),intentClass);
        startActivity(intent);
        finish();
    }

    public void IntentOnly(Class intentClass){
        Intent intent = new Intent(getApplicationContext(),intentClass);
        startActivity(intent);
        finish();
    }

    //Intent with Close all previous Activity
    public void IntentWithClosePrevious(Class intentClass){
        Intent intent = new Intent(getApplicationContext(),intentClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    //Set Edit Focusable
    public void EditTextFocus(EditText editText){
        editText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void HideKeyboard(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    public void Toolbar(){
        Toolbar toolbar = findViewById(R.id.id_toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
