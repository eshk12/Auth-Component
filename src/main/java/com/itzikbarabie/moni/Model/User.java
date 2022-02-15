package com.itzikbarabie.moni.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="users")
public class User extends BaseEntity{

    @Column(name="userId")
    private String userId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="tokens", insertable = false)
    @ElementCollection(targetClass=String.class)
    private List<String> tokens;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="permission", insertable = false, columnDefinition="tinyint(1) default 0")
    private Integer permission;

    @Column(name="expirationDate", insertable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

}
