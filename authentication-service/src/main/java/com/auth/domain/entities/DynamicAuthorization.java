package com.auth.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dynamic_authorization")
public class DynamicAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "uri")
    private String uri;

    @Column(name = "roles")
    private String roles;

    @Column(name = "method")
    private String method;

    @Column(name = "description")
    private String description;
}
