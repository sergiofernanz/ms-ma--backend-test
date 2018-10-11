package com.sergio.social.api;

public class APIName {


    // charset
    public static final String CHARSET = "application/json;charset=utf-8";

    //user api
    public static final String USERS = "/users";
    public static final String USER_REGISTER = "/register";
    
    
    //friend api
    public static final String FRIEND = "/friends";
    public static final String FRIEND_LIST = "/list";
    public static final String REQUEST_FRIEND = "/request/{destinationUsername}";
    public static final String ACCEPT_FRIEND = "/accept/{originUsername}";
    public static final String DECLINE_FRIEND = "/decline/{originUsername}";

}
