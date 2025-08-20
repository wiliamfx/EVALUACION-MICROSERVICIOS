package com.clientes.service;

import com.clientes.dto.ClienteDto;
import com.clientes.entity.Cliente;
import com.clientes.exception.ClienteException;
import com.clientes.mapper.ClienteMapper;
import com.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDto crearCliente(ClienteDto clienteDto){
        if (clienteRepository.existsBydni(clienteDto.getDni())) {
            throw new ClienteException("El DNI ya está registrado.");
        }

        if (clienteRepository.existsByEmail(clienteDto.getEmail())) {
            throw new ClienteException("El correo electrónico ya está registrado.");
        }

        Cliente cliente = clienteMapper.toEntity(clienteDto);
        Cliente guardado = clienteRepository.save(cliente);

        return clienteMapper.toDto(guardado);
    }


    public ClienteDto obtenerPorid(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("cliente no encontrado...."));
        return clienteMapper.toDto(cliente);
    }

    public List<ClienteDto> listarClientes(){
        return clienteRepository.findAll()
                .stream() //el stream convierte a una lista
                .map(clienteMapper::toDto) //transforma una funcion
                .collect(Collectors.toList());  //despues de transformar los elemtnos usamos collector para devover una lista
    }

    public ClienteDto actualizarCliente(Long id,ClienteDto clienteDto){

        Cliente cliente=clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("cliente no encontrado ..."));

        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());

        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEmail(clienteDto.getEmail());

        if (!cliente.getDni().equals(clienteDto.getDni())){
            if(clienteRepository.existsBydni(clienteDto.getDni())){
                throw new ClienteException("ya existe un cliente con ese DNI....");
            }
        }
        Cliente actualizado = clienteRepository.save(cliente);
        return clienteMapper.toDto(actualizado);

    }

    public void eliminarCliente(Long id){
        if (!clienteRepository.existsById(id)){
            throw new ClienteException("cliente no encontrado....");
        }
        clienteRepository.deleteById(id);
    }



}
