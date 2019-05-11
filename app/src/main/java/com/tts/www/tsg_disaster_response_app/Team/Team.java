package com.tts.www.tsg_disaster_response_app.Team;

import android.os.Bundle;
import android.widget.TextView;

import com.tts.www.tsg_disaster_response_app.R;
import com.tts.www.tsg_disaster_response_app.activity.BaseActivity;

public class Team extends BaseActivity {

    String SpinnerName = "";
    TextView spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Toolbar();
        setTitle("Team");

        SpinnerName = getIntent().getStringExtra("SpinnerName");
        spinner = findViewById(R.id.spinner_input);
        spinner.setText(SpinnerName);
    }
}
