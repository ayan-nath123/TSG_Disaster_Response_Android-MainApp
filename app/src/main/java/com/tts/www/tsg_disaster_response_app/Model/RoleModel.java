package com.tts.www.tsg_disaster_response_app.Model;

import androidx.annotation.NonNull;

public class RoleModel {
    //String roleId;
    String roleName;

    public RoleModel(/*String roleId,*/ String roleName) {
    //    this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleModel() {
    }

    /*public String getRoleId() {
        return roleId;
    }*/

    /*public void setRoleId(String roleId) {
        this.roleId = roleId;
    }*/

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @NonNull
    @Override
    public String toString() {
        return roleName;
    }
}
