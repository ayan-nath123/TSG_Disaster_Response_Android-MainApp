package com.tts.www.tsg_disaster_response_app.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class DRApplication extends Application {

    public SharedPreferences preferences;
    public String AppKey ="4321" ;
    public boolean session = false;
    public String accessToken,token_type,expires_in,status,response,user_id,firstName,initialLogin,temp_login,passwordExpires,systemRoleId,issued,expires;

    public enum UserData {
        LOGIN_PREF,SESSION,ACCESS_TOKEN,TOKENTYPE,EXPIRESIN,STATUS,RESPONSE,USER_ID,FIRST_NAME,INITIAL_LOGIN,TEMP_LOGIN,PASSWORD_EXPIRE,SYSTEM_ROLE_ID,ISSUED,EXPIRES
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

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.ACCESS_TOKEN.name(), this.accessToken);
        editor.commit();
    }

    public String getAccessToken(){
        return preferences.getString(UserData.ACCESS_TOKEN.name(), this.accessToken);
    }

    public void setToken_type(String tokentype){
        this.token_type = tokentype;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.TOKENTYPE.name(), this.token_type);
        editor.commit();
    }

    public String getTokenType(){
        return preferences.getString(UserData.TOKENTYPE.name(), this.token_type);
    }

    public void setExpires_in(String expiresin){
       this.expires_in = expiresin;
       SharedPreferences.Editor editor = preferences.edit();
       editor.putString(UserData.EXPIRESIN.name(), this.expires_in);
       editor.commit();
    }

    public String getExpires_in(){
        return preferences.getString(UserData.EXPIRESIN.name(), this.expires_in);
    }

    public void setStatus(String Status){
        this.status = status;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.STATUS.name(), this.status);
        editor.commit();
    }

    public String getStatus(){
        return preferences.getString(UserData.STATUS.name(), this.status);
    }

    public void setResponse(String response){
        this.response = response;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.RESPONSE.name(), this.response);
        editor.commit();
    }

    public String getResponse(){
        return preferences.getString(UserData.RESPONSE.name(), this.response);
    }

    public void setUser_id(String userId){
        this.user_id = userId;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.USER_ID.name(), this.user_id);
        editor.commit();
    }

    public String getUser_id(){
        return preferences.getString(UserData.USER_ID.name(), this.user_id);
    }

    public void setFirstName(String FirstName){
        this.firstName = FirstName;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.FIRST_NAME.name(), this.firstName);
        editor.commit();
    }

    public String getFirstName(){
        return preferences.getString(UserData.FIRST_NAME.name(), this.firstName);
    }

    public void setInitialLogin(String initialLogin){
        this.initialLogin = initialLogin;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.INITIAL_LOGIN.name(), initialLogin);
        editor.commit();
    }

    public String getInitialLogin(){
        return preferences.getString(UserData.INITIAL_LOGIN.name(), this.initialLogin);
    }

    public void setTemp_login(String temp_login){
        this.temp_login = temp_login;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.TEMP_LOGIN.name(), this.temp_login);
        editor.commit();
    }

    public String getTemp_login(){
        return preferences.getString(UserData.TEMP_LOGIN.name(), this.temp_login);
    }

   public void setPasswordExpires(String passwordExpires){
        this.passwordExpires = passwordExpires;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.PASSWORD_EXPIRE.name(), this.passwordExpires);
        editor.commit();
   }

   public String getPasswordExpires(){
        return preferences.getString(UserData.PASSWORD_EXPIRE.name(), this.passwordExpires);
   }

   public void setSystemRoleId(String systemRoleId){
        this.systemRoleId = systemRoleId;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.SYSTEM_ROLE_ID.name(), this.systemRoleId);
        editor.commit();
   }

   public String getSystemRoleId(){
        return preferences.getString(UserData.SYSTEM_ROLE_ID.name(), this.systemRoleId);
   }

   public void setIssued(String issued){
        this.issued = issued;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.ISSUED.name(), this.issued);
        editor.commit();
   }

   public String getIssued(){
        return preferences.getString(UserData.ISSUED.name(), this.issued);
   }

   public void setExpires(String expires){
        this.expires = expires;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserData.EXPIRES.name(), this.expires);
        editor.commit();
   }

   public String getExpires(){
        return preferences.getString(UserData.EXPIRES.name(), this.expires);
   }

}
