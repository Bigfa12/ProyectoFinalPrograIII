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
<<<<<<< HEAD
    ClienteRepositorio clienteRepositorio;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void listClientes() {
        clienteRepositorio.findAll();
=======
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
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCliente(long id){
        clienteServicio.eliminarClientePorID(id);
    }

    @PatchMapping("/{id}")
<<<<<<< HEAD
    @PreAuthorize("hasRole('ADMIN')")
    public void editCliente(@PathVariable long id,@RequestBody Cliente cliente) {
        if(clienteRepositorio.existsById(id)){
            clienteRepositorio.save(cliente);
        }
        else{
            System.out.println("Cliente no encontrado");
        }
=======
    public void updateCliente(@PathVariable long id,@RequestBody Cliente cliente) {
        clienteServicio.editarCliente(id,cliente);
>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284
    }




}