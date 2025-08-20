package com.clientes.mapper;

import com.clientes.dto.ClienteDto;
import com.clientes.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDto toDto(Cliente cliente){
        return new ClienteDto(
                cliente.getClienteid(),
                cliente.getNombre(),
                cliente.getApellidos(),
                cliente.getDni(),
                cliente.getEmail(),
                cliente.getTelefono()
        );

    }

    public Cliente toEntity(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setClienteid(clienteDto.getClienteid());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setDni(clienteDto.getDni());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEmail(clienteDto.getEmail());

        return cliente;
    };
}
