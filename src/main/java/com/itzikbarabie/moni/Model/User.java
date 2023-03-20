package com.itzikbarabie.moni.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="users")
public class User extends BaseEntity {

    @Column(name="userId", unique = true)
    private String userId;

    @Column(name="username", unique = true)
    private String username;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="expirationDate")
    @CreationTimestamp
    private Timestamp expirationDate;

    @Column(name="roles")
    private String roles;

}
