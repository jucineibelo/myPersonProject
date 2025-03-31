package com.stay_fine.model.entity;

import com.stay_fine.enums.ProductStatus;
import com.stay_fine.enums.ProfessionalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @Column(name = "registration_date")
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(name = "update _date")
    private LocalDateTime updateDate;

    @Builder.Default
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProfessionalStatus status = ProfessionalStatus.ATIVO;


}
