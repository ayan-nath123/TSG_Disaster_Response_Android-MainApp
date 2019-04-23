package com.tts.www.tsg_disaster_response_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tts.www.tsg_disaster_response_app.R;

public class Profile extends AppCompatActivity {

    TextView volFullName,volDateOfBirth,volEMail,volMobileNo,volOrgName,volLanguage,volState,volLocation,volEmerfullName,volEmerMobileNo,volEmerRelation;
    RadioGroup gender;
    RadioButton male,female;
    TextView upateProfile,submitProfileUpdate;

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

        upateProfile = findViewById(R.id.updateProfile);
        submitProfileUpdate = findViewById(R.id.submit_update_profile);

        upateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Update Profile", Toast.LENGTH_SHORT).show();
            }
        });
        submitProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Profile.this, "Submit Update Profile", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
