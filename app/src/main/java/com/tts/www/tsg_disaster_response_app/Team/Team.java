package com.tts.www.tsg_disaster_response_app.Team;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tts.www.tsg_disaster_response_app.Model.TeamModel;
import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.activity.BaseActivity;
import com.tts.www.tsg_disaster_response_app.adapters.TeamAdapter;
import com.tts.www.tsg_disaster_response_app.singleton.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Team extends BaseActivity {

    String SpinnerName = "";
    TextView spinner;
    TeamAdapter teamAdapter;
    ArrayList<TeamModel> teamModelArray = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Toolbar();
        setTitle("Team");

        SpinnerName = getIntent().getStringExtra("SpinnerName");
        spinner = findViewById(R.id.spinner_input);
        spinner.setText(SpinnerName);

        teamAdapter= new TeamAdapter(this,teamModelArray);
        recyclerView = findViewById(R.id.recyclerview_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(teamAdapter);
        showTeamList();

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
}
