package com.tts.www.tsg_disaster_response_app.Team;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tts.www.tsg_disaster_response_app.Model.RoleModel;
import com.tts.www.tsg_disaster_response_app.Model.TeamModel;
import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.activity.BaseActivity;
import com.tts.www.tsg_disaster_response_app.adapters.TeamAdapter;
import com.tts.www.tsg_disaster_response_app.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Team extends BaseActivity {

    String SpinnerName = "";
    TextView spinner;
    TeamAdapter teamAdapter;
    ArrayList<TeamModel> teamModelArray = new ArrayList<>();
    RecyclerView recyclerView;
    int count = 0 ;
    private DatePickerDialog startDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    //This is for loading Roles into DialogBox
    ArrayList<String> roleArrayList = new ArrayList<>();
    String[] listRoles ;
    boolean[] chekedItemsRoles;
    ArrayList<Integer> mUserItemsRoles = new ArrayList<>();
    String item="";

    // this is for loading the Organisation into DialogBox
    ArrayList<String> organisationArrayList = new ArrayList<>();
    String[] listOrganisation ;
    boolean[] chekedItemsOrganisation;
    ArrayList<Integer> mUserItemsOrganisation = new ArrayList<>();
    String itemOrg = "";

    //This is for loading the Location into the DialogBox
    ArrayList<String> locationArrayList = new ArrayList<>();
    String[] listLocation;
    boolean[] checkedItemsLocation;
    ArrayList<Integer> mUserItemsLocation = new ArrayList<>();
    String itemLoc = "";

    LinearLayout ll_role,ll_organisation,ll_start_date,ll_end_date,ll_location;
    TextView filter_list, tv_role,tv_organisation,tv_startdate,tv_enddate,tv_location,tv_submit_done;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Toolbar();
        setTitle("Team");

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        SpinnerName = getIntent().getStringExtra("SpinnerName");
        spinner = findViewById(R.id.spinner_input);
        spinner.setText(SpinnerName);

        teamAdapter= new TeamAdapter(this,teamModelArray);
        recyclerView = findViewById(R.id.recyclerview_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(teamAdapter);
        showTeamList();


        ll_role = findViewById(R.id.ll_role);
        ll_organisation = findViewById(R.id.ll_organisation);
        ll_start_date = findViewById(R.id.ll_start_date);
        ll_end_date = findViewById(R.id.ll_end_date);
        ll_location = findViewById(R.id.ll_location);

        tv_role = findViewById(R.id.tv_role);
        tv_organisation = findViewById(R.id.tv_organisation);
        tv_startdate = findViewById(R.id.tv_startdate);
        tv_enddate = findViewById(R.id.tv_enddate);
        tv_location = findViewById(R.id.tv_location);
        tv_submit_done = findViewById(R.id.tv_submit_done);
        filter_list = findViewById(R.id.filter_list);

        filter_list.setOnClickListener(this);
        ll_role.setOnClickListener(this);
        ll_organisation.setOnClickListener(this);
        ll_start_date.setOnClickListener(this);
        ll_end_date.setOnClickListener(this);
        ll_location.setOnClickListener(this);
        tv_submit_done.setOnClickListener(this);

        ShowRole();
        ShowOrganization();
        ShowLocation();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter_list:
                Toast.makeText(this, "This is toast", Toast.LENGTH_SHORT).show();
                listRoles = roleArrayList.toArray(new String[roleArrayList.size()]);
                Log.i("!!!listRoles", Arrays.toString(listRoles));
                break;
            case R.id.ll_role:
                ShowRoleName();
                break;
            case R.id.ll_organisation:
                ShowOrganisationName();
                break;
            case R.id.ll_start_date:
                startDate();
                break;
            case R.id.ll_end_date:
                endDate();
                break;
            case R.id.ll_location:
                ShowLocationName();
                break;
            case R.id.tv_submit_done:
                break;
                default:
                    break;

        }
    }

    public void showTeamList(){
        String url = "https://api.myjson.com/bins/125jo6";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = response;
                        try {
                            if(object.getString("status").equals("true")){
                                Log.d("!!! teamList", object.toString());
                                JSONArray teamArray = object.getJSONArray("response");
                                Log.d("!!! List", response.toString());
                                for(int i = 0; i < teamArray.length(); i++){
                                    JSONObject jsonObject = teamArray.getJSONObject(i);
                                    TeamModel teamModel = new TeamModel();
                                    teamModel.setTeamMemberName(jsonObject.getString("NAME"));
                                    teamModel.setTeamMembercompany(jsonObject.getString("COMPANY"));
                                    teamModel.setTeamMemberDesignation(jsonObject.getString("DESIGNATION"));
                                    teamModel.setTeamMemberPhoneNo(jsonObject.getString("PHONE"));
                                    teamModel.setTeamMemberEmail(jsonObject.getString("EMAIL"));
                                    teamModelArray.add(teamModel);
                                }
                                recyclerView.setAdapter(teamAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("!!!Error", error.toString());
                    }
                })/*{
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("Authorization", "bearer "+app.getAccessToken());
                return headers;
            }
        }*/;
        VolleySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }
    public void startDate(){
        ll_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                startDatePickerDialog = new DatePickerDialog(Team.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tv_startdate.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                startDatePickerDialog.show();
            }
        });
    }
    public void endDate(){
        Calendar newCalendar = Calendar.getInstance();
        startDatePickerDialog = new DatePickerDialog(Team.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tv_enddate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        startDatePickerDialog.show();
    }
    public void ShowRole(){
        String url = "https://api.myjson.com/bins/crx8m";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = response;
                        try {
                            if(object.getString("status").equals("true")){
                                Log.d("!!!Role", object.toString());
                                JSONArray jsonArray = object.getJSONArray("response");
                                Log.d("!!!Role", response.toString());
                                for(int i = 0; i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //    RoleModel roleModel = new RoleModel();
                                //    roleModel.setRoleId(jsonObject.getString("ROLE_ID"));
                                //    roleModel.setRoleName(jsonObject.getString("ROLE_NAME"));
                                    roleArrayList.add(jsonObject.getString("ROLE_NAME"));
                                    Log.i("!!!roleArrayList", roleArrayList.toString());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("!!!ErrorRole", error.toString());
            }
        }/*{
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("Authorization", "bearer "+app.getAccessToken());
                return headers;
            }
        }*/);
        VolleySingleton.getInstance(this).addToRequestQueue(objectRequest);

    }
    public void ShowRoleName(){
        ll_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder =new AlertDialog.Builder(Team.this);
                mBuilder.setTitle("Please Select Roles");
                mBuilder.setMultiChoiceItems(listRoles, chekedItemsRoles, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            mUserItemsRoles.add(which);
                        }else {
                            mUserItemsRoles.remove(Integer.valueOf(which));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int j = 0 ;j < mUserItemsRoles.size(); j++){
                            item = item + listRoles[mUserItemsRoles.get(j)];
                            if(j != mUserItemsRoles.size() - 1 ){
                                item = item + ",";
                            }
                        }
                    //    String some = item.replaceAll("\\s","");
                        tv_role.setText(item);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }
    public void ShowOrganization(){
        String url = "";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = response;
                        try {
                            if(object.getString("status").equals("true")){
                              Log.d("!!!Organisation", object.toString());
                              JSONArray jsonArray = object.getJSONArray("response");
                              Log.d("!!!Org", object.toString());
                              for(int i = 0; i < jsonArray.length();i++){
                                  JSONObject jsonObject = jsonArray.getJSONObject(i);
                                  organisationArrayList.add(jsonObject.getString(""));
                              }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("!!!ErrorRole", error.toString());
            }
        })/* {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("Authorization","bearer "+app.getAccessToken());
                return headers;
            }
        }*/;
        VolleySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }
    public void ShowOrganisationName(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Team.this);
        mBuilder.setTitle("Please Select Organisaiton");
        mBuilder.setMultiChoiceItems(listOrganisation, chekedItemsOrganisation, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    mUserItemsOrganisation.add(which);
                } else {
                    mUserItemsOrganisation.remove(Integer.valueOf(which));
                }
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int j = 0; j < mUserItemsOrganisation.size(); j++){
                    itemOrg = itemOrg + listOrganisation[mUserItemsOrganisation.get(j)];
                    if(j != mUserItemsOrganisation.size() - 1){
                        itemOrg = itemOrg + ",";
                    }
                }
                tv_organisation.setText(itemOrg);
            }
        });
    }
    public void ShowLocation(){
        String url = "";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = response;
                        try {
                            if(object.getString("status").equals("true")){
                                Log.d("!!!Location", object.toString());
                                JSONArray jsonArray = object.getJSONArray("response");
                                Log.d("!!!Location", object.toString());
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    locationArrayList.add(jsonObject.getString(""));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })/*{
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("Authorization", "bearer "+app.getAccessToken());
                return headers;
            }
        }*/;
        VolleySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }
    public void ShowLocationName(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Team.this);
        mBuilder.setTitle("Please Select Location");
        mBuilder.setMultiChoiceItems(listLocation, checkedItemsLocation, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    mUserItemsLocation.add(which);
                } else {
                    mUserItemsLocation.remove(Integer.valueOf(which));
                }
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int j = 0; j < mUserItemsLocation.size();j++){
                    itemLoc = itemLoc + listLocation[mUserItemsLocation.get(j)];
                    if(j != mUserItemsLocation.size() - 1){
                        itemLoc = itemLoc + ",";
                    }
                }
                tv_location.setText(itemLoc);
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
}
