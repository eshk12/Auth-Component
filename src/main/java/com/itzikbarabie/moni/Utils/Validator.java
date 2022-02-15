package com.itzikbarabie.moni.Utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    EmailValidator emailValidator = EmailValidator.getInstance();

    public boolean isValidEmail(String email) {
        return emailValidator.isValid(email);
    }

    public boolean isValidUserId(String userId) {
        String perfixedId = String.format("%9s", userId).replace(' ', '0');
        if (perfixedId.length() == 9) {
            int sum = 0;
            for (int i = 0; i < perfixedId.length(); i++) {
                int tempSum = ((i % 2 == 0) ? 1 : 2) * Character.getNumericValue(perfixedId.charAt(i));
                sum += (tempSum > 9) ? tempSum % 10 + tempSum / 10 : tempSum;
            }
            return sum % 10 == 0;
        } else {
            return false;
        }
    }
}
