package com.tts.www.tsg_disaster_response_app.Model;

public class TeamModel {

    private String teamMemberName;
    private String teamMembercompany;
    private String teamMemberDesignation;
    private String teamMemberPhoneNo;
    private String teamMemberEmail;

    public TeamModel(String teamMemberName, String teamMembercompany, String teamMemberDesignation, String teamMemberPhoneNo, String teamMemberEmail) {
        this.teamMemberName = teamMemberName;
        this.teamMembercompany = teamMembercompany;
        this.teamMemberDesignation = teamMemberDesignation;
        this.teamMemberPhoneNo = teamMemberPhoneNo;
        this.teamMemberEmail = teamMemberEmail;
    }

    public TeamModel() {
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public void setTeamMemberName(String teamMemberName) {
        this.teamMemberName = teamMemberName;
    }

    public String getTeamMembercompany() {
        return teamMembercompany;
    }

    public void setTeamMembercompany(String teamMembercompany) {
        this.teamMembercompany = teamMembercompany;
    }

    public String getTeamMemberDesignation() {
        return teamMemberDesignation;
    }

    public void setTeamMemberDesignation(String teamMemberDesignation) {
        this.teamMemberDesignation = teamMemberDesignation;
    }

    public String getTeamMemberPhoneNo() {
        return teamMemberPhoneNo;
    }

    public void setTeamMemberPhoneNo(String teamMemberPhoneNo) {
        this.teamMemberPhoneNo = teamMemberPhoneNo;
    }

    public String getTeamMemberEmail() {
        return teamMemberEmail;
    }

    public void setTeamMemberEmail(String teamMemberEmail) {
        this.teamMemberEmail = teamMemberEmail;
    }
}
