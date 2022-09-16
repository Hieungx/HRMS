package com.auth.domain.entities;

import com.auth.app.dtos.AuthUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_user")
public class AuthUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    private UUID userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "phone")
    private String phone;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    public AuthUserDTO toDTO(){
        return new AuthUserDTO(this.userId, this.username, this.fullName,this.phone,this.email,this.roles);
    }
}
