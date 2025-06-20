package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Exceptions.ClienteNoEncontradoException;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.UserRepositorio;
import com.gimnasio.demo.Service.ClienteServicio;
import com.gimnasio.demo.Service.UserServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private UserServicio userServicio;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @PostMapping("/crearCliente")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> convertirUsuarioACliente() {
        try {
            User user = userServicio.conseguirUser();
            Usuario usuario = user.getUsuario();

            if (clienteServicio.existeClientePorUsuario(usuario)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya es un cliente.");
            }

            Cliente cliente = new Cliente(true, usuario);
            clienteServicio.crearCliente(cliente);

            jdbcTemplate.update("UPDATE authorities SET authority=? WHERE username=?", "CLIENT", user.getUsername());

            return ResponseEntity.ok("Cliente creado exitosamente.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el cliente: " + e.getMessage());
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
