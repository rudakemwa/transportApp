package com.transportApp.transportApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_member;

    private String nom;
    private String prenom;
    

    @Column(name = "telephone", nullable = false, unique = true)
    private String telephone;

   
 }

