package com.example.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemNameAndPriceDto {
    private Long id;
    private String name;
    private Integer price;
}
