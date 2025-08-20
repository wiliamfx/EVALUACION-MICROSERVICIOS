package com.roles.roles.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolResponseDto {

    private Long rolid; // ID del rol
    private String rolnombre; // Nombre del rol
}
