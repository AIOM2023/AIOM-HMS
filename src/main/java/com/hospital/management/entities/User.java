package com.hospital.management.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "MASTER_USER")
public class User {

    @Id
    @Column(name= "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Integer userId;

    @Column(name="USER_NAME")
    private  String userName;

    @Column(name="PASSWORD")
    private  String password;

    @Column(name="EMAIL_ID")
    private  String email;

    @Column(name="CONTACT_NO")
    private  String contactNumber;

    @Column(name="ROLE")
    private  String role;


}


