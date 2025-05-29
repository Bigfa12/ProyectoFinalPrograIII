package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import com.gimnasio.demo.Service.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping
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
    public void deleteCliente(long id){
        clienteServicio.eliminarClientePorID(id);
    }

    @PatchMapping("/{id}")
    public void updateCliente(@PathVariable long id,@RequestBody Cliente cliente) {
        clienteServicio.editarCliente(id,cliente);
    }




}