package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Exceptions.EjercicioNoEncontradoException;
import com.gimnasio.demo.Exceptions.RutinaNoEncontradaException;
import com.gimnasio.demo.Model.EjercicioRutina;
import com.gimnasio.demo.Model.Rutina;
import com.gimnasio.demo.Service.RutinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rutina")

public class RutinaController {

    //Rutina
    @Autowired
    private RutinaServicio rutinaServicio;

    @PostMapping("/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> agregarRutina(@RequestBody Rutina rutina){
        rutinaServicio.agregarRutina(rutina);
        return ResponseEntity.ok("Rutina agregada con exito");
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('CLIENT')")
    public List<EjercicioRutina> listarRutinasYejercicios(){
        return rutinaServicio.listarEjercicio();
    }


    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarRutina(@PathVariable long id){
        try{
            rutinaServicio.eliminarRutina(id);
        } catch (RutinaNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    @PostMapping("/modificar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void modificarRutina(@PathVariable long id, @RequestBody Rutina rutina){
        try{
            rutinaServicio.modificarRutina(id, rutina);
        }catch(RutinaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String buscarRutinaID(@PathVariable long id){
        String rta ="";
        try {
            rta= rutinaServicio.buscarRutinaID(id).toString();
        }catch (RutinaNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
        return rta;
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

    @GetMapping("/buscar/ejercicio/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EjercicioRutina buscarEjercicioPorID(@PathVariable Long id){
        return rutinaServicio.buscarEjercicioID(id);
    }

    @GetMapping("/listar/ejercicio")
    @PreAuthorize("hasRole('ADMIN')")
    public void listarRutinas(){
        System.out.println(rutinaServicio.listarEjercicio());
    }
}
