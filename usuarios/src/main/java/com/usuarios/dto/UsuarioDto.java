package com.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long usuarioid; // ID del usuario
    private String username; // Nombre de usuario
    private String password;
    private String nombre;   // Nombre del usuario
    private String apellidos; // Apellidos del usuario

    @NotBlank(message = "el dni debe de ser de 8 digitos")
    private String dni;
    private RolDto rolDto;// DNI del usuario

    

}
