package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Enums.Dia;
import com.gimnasio.demo.Exceptions.EjercicioNoEncontradoException;
import com.gimnasio.demo.Exceptions.RutinaNoEncontradaException;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.EjercicioRutina;
import com.gimnasio.demo.Model.Rutina;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Service.ClienteServicio;
import com.gimnasio.demo.Service.RutinaServicio;
import com.gimnasio.demo.Service.UserServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rutina")

public class RutinaController {

    //Rutina
    @Autowired
    private RutinaServicio rutinaServicio;
    @Autowired
    private UserServicio userServicio;
    @Autowired
    private ClienteServicio clienteServicio;


    @PostMapping("/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> agregarRutina(@RequestBody Rutina rutina){
        rutinaServicio.agregarRutina(rutina);
        return ResponseEntity.ok("Rutina agregada con exito");
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('CLIENT')")
    public ResponseEntity<?> listarRutinas(){
        User user=userServicio.conseguirUser();

        if(!user.getUsername().equals("Bauti") && !user.getUsername().equals("BigFa12") && !user.getUsername().equals("FranTribu") && !user.getUsername().equals("Lean") && !user.getUsername().equals("Gon"))
        {
            if (user == null || user.getUsuario() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario no está autenticado o no está vinculado a un Cliente.");
            }

            Cliente cliente = clienteServicio.existeClientePorIdUsuario(user.getUsuario().getId());


            if(cliente.isAlDia())
            {
                return ResponseEntity.ok(rutinaServicio.listarRutinas());
            }else
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario no esta al dia. Se recomienda pagar");
            }
        }else
        {
            return ResponseEntity.ok(rutinaServicio.listarRutinas());
        }
        }


    @GetMapping("/buscar/{dia}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('CLIENT')")
    public ResponseEntity<?> buscarRutinaPorDia(@PathVariable Dia dia){
        User user=userServicio.conseguirUser();

        if(!user.getUsername().equals("Bauti") && !user.getUsername().equals("BigFa12") && !user.getUsername().equals("FranTribu") && !user.getUsername().equals("Lean") && !user.getUsername().equals("Gon"))
        {
            if (user == null || user.getUsuario() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario no está autenticado o no está vinculado a un Cliente.");
            }

            Cliente cliente = clienteServicio.existeClientePorIdUsuario(user.getUsuario().getId());

            if(cliente.isAlDia())
            {
                return ResponseEntity.ok(rutinaServicio.buscarRutinaPorDia(dia));
            }else
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El usuario no esta al dia. Se recomienda pagar");
            }
        }else
        {
            return ResponseEntity.ok(rutinaServicio.buscarRutinaPorDia(dia));
        }
    }

    //ejercicioRutina

    @PostMapping("/agregar/ejercicio/{id_rutina}")
    @PreAuthorize("hasRole('ADMIN')")
    public void agregarEjercicio(@RequestBody  EjercicioRutina ejercicioRutina,@PathVariable Long id_rutina){
        rutinaServicio.agregarEjercicio(ejercicioRutina,id_rutina);
    }

    @DeleteMapping("/eliminar/ejercicio/{id_ejercicio}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarEjercicio(@PathVariable Long id_ejercicio){
        try{
            rutinaServicio.eliminarEjercicio(id_ejercicio);
        }catch(EjercicioNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }


}
