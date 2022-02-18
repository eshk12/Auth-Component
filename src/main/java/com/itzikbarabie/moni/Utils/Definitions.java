package com.itzikbarabie.moni.Utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class Definitions {

    //USER RELATED
    public String EMAIL_IS_ALREADY_REGISTERED = "The email is already registered!";
    public String USERNAME_IS_ALREADY_REGISTERED = "The username is already registered!";
    public String USER_ID_IS_ALREADY_REGISTERED = "The userid is already registered!";
    public String USER_IS_NOT_EXISTS = "The user is not exists!";
    public String MISSING_FIELDS = "There are missing fields!";
    public String EMAIL_IS_INVALID = "The email is invalid!";
    public String USER_ID_IS_INVALID = "The User ID is invalid!";
    public String USER_ROLES_IS_INVALID = "The required user roles is invalid!";
    public String INVALID_CREDENTIAL = "Invalid details!";
    public String USER_ACTIVATED = "The user has been activated!";
    public String USER_DEACTIVATED = "The user has been deactivated!";


    //SPRING SECURITY related
    public List<String> roleList = new ArrayList<String>(){{
        add("ROLE_GUEST");
        add("ROLE_USER");
        add("ROLE_ADMIN");
    }};

    //JWT RELATED
    public String JWT_SECRET_KEY = "IAMMONITHEMONITOR";

    public final long MONTH = 1000L * 60 * 60 * 24 * 28;
    public long JwtTokenValidationDuration = MONTH;


}
