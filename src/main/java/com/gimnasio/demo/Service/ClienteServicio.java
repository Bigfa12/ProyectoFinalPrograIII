package com.gimnasio.demo.Service;

import com.gimnasio.demo.Exceptions.ClienteNoEncontradoException;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;


    public Optional<Cliente> buscarClientePorID(Long id) throws ClienteNoEncontradoException{
        Optional<Cliente> cliente;

        if(clienteRepositorio.existsById(id)){
            cliente = clienteRepositorio.findById(id);
        }else{
            throw new ClienteNoEncontradoException("ese cliente no existe");
        }
        return cliente;
    }

    public void crearCliente(Cliente cliente){
        clienteRepositorio.save(cliente);
    }

    public void eliminarClientePorID(Long id) throws ClienteNoEncontradoException{
        if (clienteRepositorio.existsById(id)){
            clienteRepositorio.deleteById(id);
        }else{
            throw new ClienteNoEncontradoException("ese cliente no existe");
        }
    }

    public List<Cliente> listarClientes(){
        return clienteRepositorio.findAll();
    }


    public void editarCliente(Long id, Cliente cliente) throws ClienteNoEncontradoException{
        if(clienteRepositorio.existsById(id)){
            clienteRepositorio.save(cliente);
        }else{
            throw new ClienteNoEncontradoException("ese cliente no existe");
        }
  
    }

    public boolean existeClientePorUsuario(Usuario usuario) {
        return clienteRepositorio.existsByUsuario(usuario);
    }

    public Cliente existeClientePorIdUsuario(Long idUsuario)
    {
        return clienteRepositorio.findByUsuario_Id(idUsuario);
    }
}
