package com.itzikbarabie.moni.Utils;

import com.itzikbarabie.moni.Exceptions.CustomException;
import com.itzikbarabie.moni.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class objectValidator {
    @Autowired
    private ErrorMessages errorMessages;
    @Autowired
    private Validator validator;

    public void isValidUserObject(User user) throws CustomException {
        if(
                user.getUserId() != null &&
                user.getEmail() != null &&
                user.getPassword() != null &&
                user.getFirstName() != null &&
                user.getLastName() != null
        ) {
            if(
                    user.getUserId().isEmpty() ||
                    user.getEmail().isEmpty() ||
                    user.getPassword().isEmpty() ||
                    user.getFirstName().isEmpty() ||
                    user.getLastName().isEmpty()
            ) {
                throw new CustomException(errorMessages.MISSING_FIELDS);
            }else{
                if(!validator.isValidEmail(user.getEmail())){
                    throw new CustomException(errorMessages.EMAIL_IS_INVALID);
                }
                if (!validator.isValidUserId(user.getUserId())){
                    throw new CustomException(errorMessages.USER_ID_IS_INVALID);
                }
            }
        }else{
            throw new CustomException(errorMessages.MISSING_FIELDS);
        }
    }
}
