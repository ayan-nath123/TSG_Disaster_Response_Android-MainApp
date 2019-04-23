package com.tts.www.tsg_disaster_response_app.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class DRApplication extends Application {

    public SharedPreferences preferences;
    public String AppKey ="4321" ;
    public boolean session = false;
    public String userId,userName,address,phoneNumber,eMail,gender,password,accessToken;

    public enum UserData {
        LOGIN_PREF,SESSION,USER_ID,USERNAME,ADDRESS,PHONENO,EMAIL,GENDER,PASSWORD,PASSWORD_SAVE,ACCESS_TOKEN
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferences =getSharedPreferences(UserData.LOGIN_PREF.name(), Context.MODE_PRIVATE);
    }

    public boolean isSession(){
        return preferences.getBoolean(UserData.SESSION.name(),this.session);
    }
    public void setSession(boolean session){
        this.session = session;
        SharedPreferences.Editor editor =preferences.edit();
        editor.putBoolean(UserData.SESSION.name(), this.session);
        editor.commit();
    }

    public void setUserId(String userId){
        this.userId = userId;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.USER_ID.name(), this.userId);
        editor.commit();
    }

    public String getUserId() {
        return preferences.getString(UserData.USER_ID.name(), this.userId);
    }

    public void setUserName(String userName){
        this.userName = userName;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.USERNAME.name(), this.userName);
        editor.commit();
    }

    public String getUserName(){
        return preferences.getString(UserData.USERNAME.name(), this.userName);
    }

    public void setAddress(String address){
        this.address = address;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.ADDRESS.name(), this.address);
        editor.commit();
    }

    public String getAddress(){
        return preferences.getString(UserData.ADDRESS.name(), this.address);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.PHONENO.name(), this.phoneNumber);
        editor.commit();
    }

    public String getPhoneNumber(){
        return preferences.getString(UserData.PHONENO.name(),this.phoneNumber);
    }

    public void seteMail(String eMail){
        this.eMail = eMail;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.EMAIL.name(), this.eMail);
        editor.commit();
    }

    public String geteMail(){
        return preferences.getString(UserData.EMAIL.name(), this.eMail);
    }

    public void setGender(String gender){
        this.gender = gender;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.GENDER.name(), this.gender);
        editor.commit();
    }

    public String getGender(){
        return preferences.getString(UserData.GENDER.name(), this.gender);
    }

    public void setPassword(String password){
        this.password = password;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.PASSWORD.name(), this.password);
        editor.commit();
    }

    public String getPassword(){
        return preferences.getString(UserData.PASSWORD.name(), this.password);
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.ACCESS_TOKEN.name(), this.accessToken);
        editor.commit();
    }

    public String getAccessToken(){
        return preferences.getString(UserData.ACCESS_TOKEN.name(), this.accessToken);
    }
}
