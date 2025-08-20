package com.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private  Long clienteid;

    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "el apellido es obligatorio")
    private String apellidos;

    @NotBlank(message = "el dni no puede ser vacio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El telefono debe tener exactamente 9 dígitos")
    private String telefono;
}
