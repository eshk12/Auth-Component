package com.itzikbarabie.moni.Utils;

import com.itzikbarabie.moni.Entity.AuthenticationRequest;
import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ObjectValidator {
    @Autowired
    private Definitions definitions;
    @Autowired
    private Validator validator;

    public void isValidString(String str){
        if(str != null){
            if(str.isEmpty()){
                throw new CustomException(definitions.MISSING_FIELDS);
            }
        }else{
            throw new CustomException(definitions.MISSING_FIELDS);
        }
    }
    public void isValidRole(String roles){
        List<String> roleList = Arrays.asList(roles.split(","));
        if(!definitions.roleList.containsAll(roleList)){
            throw new CustomException(definitions.USER_ROLES_IS_INVALID);
        }
    }
    public void isValidObjectId(long objectId) throws CustomException{
        if(objectId <= 0){
            throw new CustomException(definitions.MISSING_FIELDS);
        }
    }

    public void isValidUserObject(User user) throws CustomException {
        if(
                user.getUserId() != null &&
                user.getUsername() != null &&
                user.getEmail() != null &&
                user.getPassword() != null &&
                user.getFirstName() != null &&
                user.getLastName() != null &&
                user.getRoles() != null
        ) {
            if(
                    user.getUserId().isEmpty() ||
                    user.getUsername().isEmpty() ||
                    user.getEmail().isEmpty() ||
                    user.getPassword().isEmpty() ||
                    user.getFirstName().isEmpty() ||
                    user.getLastName().isEmpty() ||
                    user.getRoles().isEmpty()
            ) {
                throw new CustomException(definitions.MISSING_FIELDS);
            }else{
                if(!validator.isValidEmail(user.getEmail())){
                    throw new CustomException(definitions.EMAIL_IS_INVALID);
                }
                if (!validator.isValidUserId(user.getUserId())){
                    throw new CustomException(definitions.USER_ID_IS_INVALID);
                }
                this.isValidRole(user.getRoles());
            }
        }else{
            throw new CustomException(definitions.MISSING_FIELDS);
        }
    }
}
