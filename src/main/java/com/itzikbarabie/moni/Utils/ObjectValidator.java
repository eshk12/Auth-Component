package com.itzikbarabie.moni.Utils;

import com.itzikbarabie.moni.Entity.AuthenticationRequest;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectValidator {
    @Autowired
    private Definitions definitions;
    @Autowired
    private Validator validator;

    public void isValidUserObject(User user) throws CustomException {
        if(
                user.getUserId() != null &&
                user.getUsername() != null &&
                user.getEmail() != null &&
                user.getPassword() != null &&
                user.getFirstName() != null &&
                user.getLastName() != null
        ) {
            if(
                    user.getUserId().isEmpty() ||
                    user.getUsername().isEmpty() ||
                    user.getEmail().isEmpty() ||
                    user.getPassword().isEmpty() ||
                    user.getFirstName().isEmpty() ||
                    user.getLastName().isEmpty()
            ) {
                throw new CustomException(definitions.MISSING_FIELDS);
            }else{
                if(!validator.isValidEmail(user.getEmail())){
                    throw new CustomException(definitions.EMAIL_IS_INVALID);
                }
                if (!validator.isValidUserId(user.getUserId())){
                    throw new CustomException(definitions.USER_ID_IS_INVALID);
                }
            }
        }else{
            throw new CustomException(definitions.MISSING_FIELDS);
        }
    }

    public void isValidJwtRequestObject(AuthenticationRequest authenticationRequest) throws CustomException {
        if(
            authenticationRequest.getUsername() != null &&
            authenticationRequest.getPassword() != null
        ) {
            if(
                authenticationRequest.getUsername().isEmpty() ||
                authenticationRequest.getPassword().isEmpty()
            ){
                throw new CustomException(definitions.MISSING_FIELDS);
            }
        }else{
            throw new CustomException(definitions.MISSING_FIELDS);
        }
    }
}
