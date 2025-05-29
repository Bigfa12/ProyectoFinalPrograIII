package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    ClienteRepositorio clienteRepositorio;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void listClientes() {
        clienteRepositorio.findAll();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCliente(long id){
        clienteRepositorio.deleteById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editCliente(@PathVariable long id,@RequestBody Cliente cliente) {
        if(clienteRepositorio.existsById(id)){
            clienteRepositorio.save(cliente);
        }
        else{
            System.out.println("Cliente no encontrado");
        }
    }




}