package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @GetMapping
    public void listClientes() {
        clienteRepositorio.findAll();
    }

    @DeleteMapping
    public void deleteCliente(long id){
        clienteRepositorio.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void editCliente(@PathVariable long id,@RequestBody Cliente cliente) {
        if(clienteRepositorio.existsById(id)){
            clienteRepositorio.save(cliente);
        }
        else{
            System.out.println("Cliente no encontrado");
        }
    }




}