package com.itzikbarabie.moni.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="permission", columnDefinition="tinyint(1) default 0")
    private int permission;

    @Column(name="expirationDate")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

}
