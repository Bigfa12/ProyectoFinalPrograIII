package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServicio {
    @Autowired
    ClienteRepositorio clienteRepositorio;

    public Optional<Cliente> buscarClientePorID(Long id){
        return clienteRepositorio.findById(id);
    }

    public void crearCliente (Cliente cliente){
        clienteRepositorio.save(cliente);
    }

    public void eliminarClientePorID(){

    }


}
