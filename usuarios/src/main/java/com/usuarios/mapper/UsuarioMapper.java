package com.usuarios.mapper;

import com.usuarios.dto.RolDto;
import com.usuarios.dto.UsuarioDto;
import com.usuarios.entity.Usuarios;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuarios usuarios, RolDto rolDto) {
        return new UsuarioDto(
                usuarios.getUsuarioid(),
                usuarios.getUsername(),
                usuarios.getPassword(),
                usuarios.getNombre(),
                usuarios.getApellidos(),
                usuarios.getDni(),
                rolDto
        );
    }

    public Usuarios toEntity(UsuarioDto usuarioDto) {
        Usuarios usuarios = new Usuarios();
        usuarios.setUsuarioid(usuarioDto.getUsuarioid());
        usuarios.setNombre(usuarioDto.getNombre());
        usuarios.setApellidos(usuarioDto.getApellidos());
        usuarios.setPassword(usuarioDto.getPassword());
        usuarios.setDni(usuarioDto.getDni());
        usuarios.setUsername(usuarioDto.getUsername());

        if (usuarioDto.getRolDto() != null) {
            usuarios.setRolid(usuarioDto.getRolDto().getRolid());
        }
        return usuarios;
    }
}
