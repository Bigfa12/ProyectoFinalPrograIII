package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Exceptions.ClienteNoEncontradoException;
import com.gimnasio.demo.Model.Cliente;
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


    @PostMapping("/insert")
    public void crearCliente(@RequestBody Cliente cliente) {
        clienteServicio.crearCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCliente(@PathVariable long id){
        try{
            clienteServicio.eliminarClientePorID(id);
        }catch (ClienteNoEncontradoException e){
            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Cliente> buscarClientePorID(@PathVariable Long id){
        return clienteServicio.buscarClientePorID(id);
    }




    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        cliente.setId_cliente(id);
        clienteServicio.editarCliente(cliente.getId_cliente(), cliente);
    }
}
