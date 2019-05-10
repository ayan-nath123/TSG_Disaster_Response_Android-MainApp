package com.tts.www.tsg_disaster_response_app.Model;

import androidx.annotation.NonNull;

public class DisasterCode {
    private String disasterCode;
    private String disasterId;

    public DisasterCode(String disasterCode, String disasterId) {
        this.disasterCode = disasterCode;
        this.disasterId = disasterId;
    }

    public DisasterCode() {
    }

    public String getDisasterCode() {
        return disasterCode;
    }

    public void setDisasterCode(String disasterCode) {
        this.disasterCode = disasterCode;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(String disasterId) {
        this.disasterId = disasterId;
    }

    @NonNull
    @Override
    public String toString() {
        return disasterCode;
    }
}
