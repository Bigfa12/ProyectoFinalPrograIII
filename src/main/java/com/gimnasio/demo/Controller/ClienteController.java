package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import com.gimnasio.demo.Service.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Cliente> listClientes() {
        return clienteServicio.listarClientes();
    }

    @PostMapping("/insert")
    public void crearCliente(Cliente cliente){
        clienteServicio.crearCliente(cliente);
    }

    @GetMapping("/{id}")
    public Optional<Cliente> buscarClientePorID(Long id){
        return clienteServicio.buscarClientePorID(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCliente(long id){
        clienteServicio.eliminarClientePorID(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editCliente(@PathVariable long id,@RequestBody Cliente cliente) {
            cliente.setId_cliente(id);
            clienteServicio.editarCliente(cliente.getId_cliente(), cliente);
        }
    }