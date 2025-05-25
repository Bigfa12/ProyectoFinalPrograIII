package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {
    @Autowired
    ClienteRepositorio clienteRepositorio;

    public Optional<Cliente> buscarClientePorID(Long id){
        return clienteRepositorio.findById(id);
    }

    public void crearCliente (Cliente cliente){
        clienteRepositorio.save(cliente);
    }

    public void eliminarClientePorID(Long id){
        clienteRepositorio.deleteById(id);
    }

    public List<Cliente> listarClientes(){
        return clienteRepositorio.findAll();
    }


}
