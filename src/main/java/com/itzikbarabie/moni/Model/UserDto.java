package com.itzikbarabie.moni.Model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto extends BaseEntityDto{
    private String userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String roles;
    private Date expirationDate;

}
