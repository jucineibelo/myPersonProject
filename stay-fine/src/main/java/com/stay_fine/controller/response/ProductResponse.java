package com.stay_fine.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String description;

    private Double price;

    private String status;

    private LocalDateTime regisrationDate;

    private LocalDateTime updateDate;
}
