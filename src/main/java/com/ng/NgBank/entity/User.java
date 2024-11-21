package com.ng.NgBank.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends Base{

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private String dob;
    private String gender;




    @OneToOne(cascade = CascadeType.PERSIST)
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;



}
