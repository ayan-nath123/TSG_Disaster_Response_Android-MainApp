package com.tts.www.tsg_disaster_response_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import es.dmoral.toasty.Toasty;

public class Profile extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    TextView volFullName, volDateOfBirth, volEMail, volMobileNo, volOrgName, volLanguage, volState, volLocation, volEmerfullName, volEmerMobileNo, volEmerRelation;
    RadioGroup gender;
    RadioButton male, female;
    TextView upateProfile, submitProfileUpdate;
    LinearLayout ll_updateProfile, ll_submitUpdate;
    String FirstName, MiddleName, LastName, DateOfBirth, Email, Gender, MobileNo, OrgName, Language, State, Location, EmerFullName, EmerMobileNo, EmerRelation;
    private String setgender = "M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        volFullName = findViewById(R.id.et_prof_fullName);
        volDateOfBirth = findViewById(R.id.et_prof_DateofBirth);
        volEMail = findViewById(R.id.et_prof_email);
        volMobileNo = findViewById(R.id.et_prof_Mobile);
        volOrgName = findViewById(R.id.et_prof_OrganisationName);
        volLanguage = findViewById(R.id.et_prof_language);
        volState = findViewById(R.id.et_prof_state);
        volLocation = findViewById(R.id.et_prof_location);
        volEmerfullName = findViewById(R.id.et_prof_emer_name);
        volEmerMobileNo = findViewById(R.id.et_prof_emer_mobile);
        volEmerRelation = findViewById(R.id.et_prof_emer_relation);

        gender = findViewById(R.id.rg_prof_gender);
        male = findViewById(R.id.rb_prof_male);
        female = findViewById(R.id.rb_prof_female);

        gender.setOnCheckedChangeListener(this);

        upateProfile = findViewById(R.id.updateProfile);
        submitProfileUpdate = findViewById(R.id.submit_update_profile);

        ll_updateProfile = findViewById(R.id.ll_update);
        ll_submitUpdate = findViewById(R.id.ll_submit_update);

        upateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfile();
            }
        });
        submitProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(Profile.this, "This is Under Developement",Toast.LENGTH_SHORT,true).show();
        //        SubmitUpdateProfile();
            }
        });
        ShowProfile();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_prof_male:
                setgender = "M";
                break;
            case R.id.rb_prof_female:
                setgender = "F";
                break;
        }

    }

    public void ShowProfile() {
        String url = "https://api.myjson.com/bins/woda8";
        showProgressDailog();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("true")) {
                                dismissProgressDialog();
                                JSONArray reqarr = response.getJSONArray("response");
                                JSONObject reqobj = reqarr.getJSONObject(0);
                                FirstName = reqobj.getString("FirstName");
                                MiddleName = reqobj.getString("MiddleName");
                                LastName = reqobj.getString("LastName");
                                DateOfBirth = reqobj.getString("DOB");
                                Email = reqobj.getString("Email");
                                Gender = reqobj.getString("Gender");
                                MobileNo = reqobj.getString("Contact_No");
                                OrgName = reqobj.getString("OrganizationName");
                                Language = reqobj.getString("Language");
                                State = reqobj.getString("State");
                                Location = reqobj.getString("Location");
                                EmerFullName = reqobj.getString("EmerFullName");
                                EmerMobileNo = reqobj.getString("EmerMobileNo");
                                EmerRelation = reqobj.getString("EmerRelation");

                                volFullName.setText(FirstName + " " + MiddleName + " " + LastName);
                                volDateOfBirth.setText(DateOfBirth);
                                volEMail.setText(Email);
                                volMobileNo.setText(MobileNo);
                                volOrgName.setText(OrgName);
                                volLanguage.setText(Language);
                                volState.setText(State);
                                volLocation.setText(Location);
                                volEmerfullName.setText(EmerFullName);
                                volEmerMobileNo.setText(EmerMobileNo);
                                volEmerRelation.setText(EmerRelation);

                                if (Gender.equalsIgnoreCase("Male")) {
                                    male.setChecked(true);
                                    female.setChecked(false);
                                } else {
                                    female.setChecked(true);
                                    male.setChecked(false);
                                }

                                volFullName.setEnabled(false);
                                volDateOfBirth.setEnabled(false);
                                volEMail.setEnabled(false);
                                volMobileNo.setEnabled(false);
                                volOrgName.setEnabled(false);
                                volLanguage.setEnabled(false);
                                volState.setEnabled(false);
                                volLocation.setEnabled(false);
                                volEmerfullName.setEnabled(false);
                                volEmerMobileNo.setEnabled(false);
                                volEmerRelation.setEnabled(false);
                                male.setEnabled(false);
                                female.setEnabled(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, "You heve no internet connection to see your Profile", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }

    public void UpdateProfile() {
        ll_updateProfile.setVisibility(View.GONE);
        ll_submitUpdate.setVisibility(View.VISIBLE);

        volFullName.setEnabled(true);
        volDateOfBirth.setEnabled(true);
        volEMail.setEnabled(true);
        volMobileNo.setEnabled(true);
        volOrgName.setEnabled(true);
        volLanguage.setEnabled(true);
        volState.setEnabled(true);
        volLocation.setEnabled(true);
        volEmerfullName.setEnabled(true);
        volEmerMobileNo.setEnabled(true);
        volEmerRelation.setEnabled(true);
        male.setEnabled(true);
        female.setEnabled(true);
    }

    public void SubmitUpdateProfile() {
        final String url = "";

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("", volFullName.getText().toString());
            jsonBody.put("", volDateOfBirth.getText().toString());
            jsonBody.put("", setgender.equalsIgnoreCase("M")?"Male":"Female");
            jsonBody.put("", volEMail.getText().toString());
            jsonBody.put("", volMobileNo.getText().toString());
            jsonBody.put("", volOrgName.getText().toString());
            jsonBody.put("", volLanguage.getText().toString());
            jsonBody.put("", volState.getText().toString());
            jsonBody.put("", volLocation.getText().toString());
            jsonBody.put("", volEmerfullName.getText().toString());
            jsonBody.put("", volEmerMobileNo.getText().toString());
            jsonBody.put("", volEmerRelation.getText().toString());
            final String requestBody = new JSONArray().put(jsonBody).toString();

            showProgressDailog();

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject =new JSONObject(response);
                                if(jsonObject.getBoolean("status")){
                                    dismissProgressDialog();
                                    Toasty.success(Profile.this, "Updated Sucessfully", Toasty.LENGTH_SHORT,true ).show();
                                    finish();
                                } else {
                                    Toasty.error(Profile.this, "Failed to Update",Toast.LENGTH_SHORT,true).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        Log.i("!!!Request", url+"    "+requestBody.getBytes("utf-8"));
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(postRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
