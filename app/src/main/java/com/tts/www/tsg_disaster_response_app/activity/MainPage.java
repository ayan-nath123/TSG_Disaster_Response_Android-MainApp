package com.tts.www.tsg_disaster_response_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.tts.www.tsg_disaster_response_app.BuildConfig;
import com.tts.www.tsg_disaster_response_app.Model.DisasterCode;
import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.constant.Constant;
import com.tts.www.tsg_disaster_response_app.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainPage extends BaseActivity {

    LinearLayout llprofile,llTeam,llMyActivity,llApproval,llDashBoard,llEmergencyPhase,llReliefPhase,llView8,llguidelines,llView10,llLogout,llClose;
    ImageView navigation,cloud;
    TextView userName,version;
    String itempos = "";
    ArrayList<DisasterCode> disasterArrayList = new ArrayList<>();
    String disastercodeId="";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        llprofile = (LinearLayout) findViewById(R.id.ll_profile);
        llTeam = (LinearLayout) findViewById(R.id.ll_team);
        llMyActivity = (LinearLayout) findViewById(R.id.ll_myActivity);
        llApproval = (LinearLayout) findViewById(R.id.ll_approval);
        llView10 = (LinearLayout) findViewById(R.id.ll_view10);
        llView8 = (LinearLayout) findViewById(R.id.ll_view8);
        llEmergencyPhase = (LinearLayout) findViewById(R.id.ll_emergencyphase);
        llReliefPhase = (LinearLayout) findViewById(R.id.ll_reliefphase);
        llguidelines = (LinearLayout) findViewById(R.id.ll_guidelines);
        llDashBoard = (LinearLayout) findViewById(R.id.ll_dashboard);
        llLogout = (LinearLayout) findViewById(R.id.ll_logout);
        llClose = (LinearLayout) findViewById(R.id.ll_close);
        navigation = (ImageView) findViewById(R.id.image_navigate);
        cloud = (ImageView) findViewById(R.id.image_cloud);

        userName = findViewById(R.id.tv_user_name);
        version = findViewById(R.id.tv_version);

        version.setText(BuildConfig.VERSION_NAME);
        userName.setText(app.getFirstName());

        llprofile.setOnClickListener(this);
        llTeam.setOnClickListener(this);
        llMyActivity.setOnClickListener(this);
        llApproval.setOnClickListener(this);
        llView10.setOnClickListener(this);
        llView8.setOnClickListener(this);
        llEmergencyPhase.setOnClickListener(this);
        llReliefPhase.setOnClickListener(this);
        llguidelines.setOnClickListener(this);
        llDashBoard.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        llClose.setOnClickListener(this);
        navigation.setOnClickListener(this);
        cloud.setOnClickListener(this);

        /*Material Spinner*/
        /*MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("FL_UK_MAR2018","SU_WB_AUG2018","EQ_OR_SEP2017","LS_TN_JAN2019", "TO_TL_JUL2018");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                itempos = item;
            }
        });*/
        spinner = findViewById(R.id.disaster_code);
        SetDisasterCode();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_profile:
                IntentOnly(Profile.class);
                break;
            case R.id.ll_team:
                Toasty.info(this, "This is team view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_myActivity:
                Toasty.info(this, "This is Activity view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_approval:
                Toasty.info(this, "This is Approval view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_view10:
                Toasty.info(this, "This is View10 view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_view8:
                Toasty.info(this, "This is View8 view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_emergencyphase:
                Toasty.info(this, "This is Emergency  view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_reliefphase:
                Toasty.info(this, "This is Relief view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_guidelines:
                Toasty.info(this, "This is guidelines view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_dashboard:
                Toasty.info(this, "This is Dashboard view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_logout:
                Toasty.info(this, "This is Logout view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.ll_close:
                Toasty.info(this, "This is close view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.image_navigate:
                Toasty.info(this, "This is Navigate view", Toasty.LENGTH_SHORT,true).show();
                break;
            case R.id.image_cloud:
                Toasty.info(this, "This is Cloud view", Toasty.LENGTH_SHORT,true).show();
                break;
        }
    }

    public void SetDisasterCode(){
        String url = "https://api.myjson.com/bins/18v246";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response;
                            if (object.getString("status").equals("true")){
                                Log.d("!!! disasterCode",object.toString());
                                JSONArray jsonArray = object.getJSONArray("response");
                                Log.d("!!! disasterCode",response.toString());
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    DisasterCode disasterCode = new DisasterCode();
                                    disasterCode.setDisasterCode(jsonObject.getString("LOV_NAME"));
                                    disasterCode.setDisasterId(jsonObject.getString("LOV_ID"));
                                    disasterArrayList.add(disasterCode);
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter<DisasterCode> disasterArrayAdapter = new ArrayAdapter<DisasterCode>(MainPage.this,android.R.layout.simple_list_item_1,disasterArrayList);
                        disasterArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(disasterArrayAdapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position != 0 ){
                                    disastercodeId = disasterArrayList.get(position).getDisasterId();

                                    Log.d("!!!countries",disastercodeId.toString());
                                }
                                else {
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        VolleySingleton.getInstance(this).addToRequestQueue(getRequest);
    }

}
