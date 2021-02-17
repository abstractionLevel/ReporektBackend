package com.example.ReportPlayer.models.role;

public enum RoleType {

    ADMIN_ROLE,USER_ROLE;


    @Override
    public String toString() {
        switch (this) {
            case ADMIN_ROLE: return "ADMIN_ROLE";
            case USER_ROLE: return  "USER_ROLE";
            default:       return "unspecified";
        }
    }


}
