package com.stay_fine.model.entity;

import com.stay_fine.enums.PersonStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Builder.Default
    @Column(name = "registration_date")
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Builder.Default
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PersonStatus status = PersonStatus.ATIVO;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
