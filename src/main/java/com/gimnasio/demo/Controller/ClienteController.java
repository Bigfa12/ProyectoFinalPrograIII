package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.PlanDTO;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/crearCliente")
    @PreAuthorize("hasAnyAuthority('USER', 'CLIENT')")
    public ResponseEntity<?> crearClienteSiPaga(@RequestBody PlanDTO dto) {
        try {
            String plan = dto.getPlan();
            User user = userServicio.conseguirUser();
            Usuario usuario = user.getUsuario();

            LocalDate hoy = LocalDate.now();
            LocalDate vence;
            if(plan.equalsIgnoreCase("anual"))
            {
                vence = hoy.plusYears(1);
            }
            else {
                vence = hoy.plusMonths(1);
            }

            if (clienteServicio.existeClientePorUsuario(usuario)) {
                Cliente cliente = clienteServicio.obtenerPorUsuario(usuario);

                if (cliente.isAlDia()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya sos cliente y estás al día. No es necesario realizar un pago.");
                }
                else
                {
                    cliente.setAlDia(true);
                    cliente.setFechaVencimiento(vence);
                    jdbcTemplate.update("UPDATE cliente SET al_dia=true WHERE id_cliente=?", cliente.getIdCliente());
                    jdbcTemplate.update("UPDATE cliente set fecha_vencimiento = ? where id_cliente =?",vence,cliente.getIdCliente());
                    return ResponseEntity.ok("Pago exitoso");
                }
            }else
            {
                Cliente nuevoCliente = new Cliente(true, usuario,vence);
                clienteServicio.crearCliente(nuevoCliente);

                jdbcTemplate.update("UPDATE authorities SET authority=? WHERE username=?", "CLIENT", user.getUsername());

                return ResponseEntity.ok("Cliente creado exitosamente y pago registrado.");
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el cliente: " + e.getMessage());
        }
    }
    @Scheduled(cron = "0 0 0 * * *", zone = "America/Argentina/Buenos_Aires")
    public void actualizarEstadosDeClientes() {
        List<Cliente> clientes = clienteServicio.listarClientes();

        for (Cliente c : clientes) {

            if (c.isAlDia() && c.getFechaVencimiento().isBefore(LocalDate.now())) {

                jdbcTemplate.update("UPDATE cliente SET al_dia=false WHERE id_cliente=?", c.getIdCliente());
            }
        }
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> buscarClientePorID(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteServicio.buscarClientePorID(id);

        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente con ID " + id + " no fue encontrado.");
        }
    }

}
