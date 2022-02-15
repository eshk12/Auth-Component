package com.itzikbarabie.moni.Utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ErrorMessages {
    public String EMAIL_IS_ALREADY_REGISTERED = "The email is already registered!";
    public String USER_IS_NOT_EXISTS = "The user is not exists!";
    public String MISSING_FIELDS = "There are missing fields!";
    public String EMAIL_IS_INVALID = "The email is invalid!";
    public String USER_ID_IS_INVALID = "The User ID is invalid!";
}
