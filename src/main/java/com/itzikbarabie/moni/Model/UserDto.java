package com.itzikbarabie.moni.Model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto extends BaseEntityDto{
    private String userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int permission;
    private Date expirationDate;

    public long getExpirationDate() {
        return expirationDate.getTime();
    }
}
