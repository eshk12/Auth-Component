package com.itzikbarabie.moni.Model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private long objectId;
    private Date createdDate;
    private Date updatedDate;
    private Date expirationDate;
    private String userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int permission;
    private boolean deleted;

}
