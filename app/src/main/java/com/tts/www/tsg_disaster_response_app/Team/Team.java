package com.tts.www.tsg_disaster_response_app.Team;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tts.www.tsg_disaster_response_app.Model.TeamModel;
import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.activity.BaseActivity;
import com.tts.www.tsg_disaster_response_app.adapters.TeamAdapter;

import java.util.ArrayList;

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

    }

    
}
