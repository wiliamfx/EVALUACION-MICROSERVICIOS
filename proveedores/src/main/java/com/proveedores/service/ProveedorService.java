package com.proveedores.service;

import com.proveedores.dto.ProveedorRequestDto;
import com.proveedores.dto.ProveedorResponseDto;
import com.proveedores.entity.Proveedor;
import com.proveedores.exepction.ProveedorException;
import com.proveedores.mapper.ProveedorMappe;
import com.proveedores.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMappe proveedorMapper;

    // CREATE
    public ProveedorResponseDto crearProveedor(ProveedorRequestDto dto) throws Exception {
        if (proveedorRepository.existsByEmail(dto.getEmail())) {
            throw new ProveedorException("El email ya está registrado.");
        }
        if (proveedorRepository.existsByRuc(dto.getRuc())) {
            throw new ProveedorException("El RUC ya está registrado.");
        }
        if (proveedorRepository.existsByTelefono(dto.getTelefono())) {
            throw new ProveedorException("El teléfono ya está registrado.");
        }

        Proveedor proveedor = proveedorMapper.toEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        return proveedorMapper.toDto(guardado);
    }

    // READ (obtener todos)
    public List<ProveedorResponseDto> listarProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream()
                .map(proveedorMapper::toDto)
                .collect(Collectors.toList());
    }

    // READ (obtener uno por ID)
    public ProveedorResponseDto buscarPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException("Proveedor con ID " + id + " no encontrado."));
        return proveedorMapper.toDto(proveedor);
    }

    // UPDATE
    public ProveedorResponseDto actualizarProveedor(Long id, ProveedorRequestDto dto) {
        Proveedor proveedorExistente = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException("Proveedor con ID " + id + " no encontrado."));

        // Puedes validar si los campos fueron cambiados y están duplicados
        if (!proveedorExistente.getEmail().equals(dto.getEmail()) &&
                proveedorRepository.existsByEmail(dto.getEmail())) {
            throw new ProveedorException("El email ya está registrado.");
        }

        if (!proveedorExistente.getRuc().equals(dto.getRuc()) &&
                proveedorRepository.existsByRuc(dto.getRuc())) {
            throw new ProveedorException("El RUC ya está registrado.");
        }

        if (!proveedorExistente.getTelefono().equals(dto.getTelefono()) &&
                proveedorRepository.existsByTelefono(dto.getTelefono())) {
            throw new ProveedorException("El teléfono ya está registrado.");
        }

        proveedorExistente.setEmail(dto.getEmail());
        proveedorExistente.setNombre(dto.getNombre());
        proveedorExistente.setRuc(dto.getRuc());
        proveedorExistente.setTelefono(dto.getTelefono());

        Proveedor actualizado = proveedorRepository.save(proveedorExistente);
        return proveedorMapper.toDto(actualizado);
    }

    // DELETE
    public void eliminarProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new ProveedorException("Proveedor con ID " + id + " no existe.");
        }
        proveedorRepository.deleteById(id);
    }
}
