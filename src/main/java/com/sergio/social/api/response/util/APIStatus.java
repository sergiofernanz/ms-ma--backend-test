package com.sergio.social.api.response.util;

public enum APIStatus {

    ERR_USER_NOT_EXIST(110, "User does not exist"),
    USER_ALREADY_EXIST(111, "Email already exist"),
    USER_NOT_VISIBLE(112, "User is not visible"),

    
    // Common status
    OK(200, null);

    private final int code;
    private final String description;

    private APIStatus(int s, String v) {
        code = s;
        description = v;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
