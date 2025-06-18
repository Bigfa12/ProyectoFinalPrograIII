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
@RequestMapping("/rutina")
public class RutinaController {

    //Rutina
    @Autowired
    private RutinaServicio rutinaServicio;

    @GetMapping
    public List<EjercicioRutina> listarRutinasYejercicios(){
        return rutinaServicio.listarEjercicio();
    }

    @PostMapping("/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public void agregarRutina(Rutina rutina){
        rutinaServicio.agregarRutina(rutina);
    }

    @DeleteMapping("/eliminar")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarRutina(long id){
        try{
            rutinaServicio.eliminarRutina(id);
        } catch (RutinaNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    @PostMapping("/modificar")
    @PreAuthorize("hasRole('ADMIN')")
    public void modificarRutina(long id, Rutina rutina){
        try{
            rutinaServicio.modificarRutina(id, rutina);
        }catch(RutinaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN')")
    public Rutina buscarRutinaID(long id){
        Rutina rutinaa = null;
        try{
            rutinaa = rutinaServicio.buscarRutinaID(id);

        }catch(RutinaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
        return rutinaa;
    }




    //ejercicioRutina

    @PostMapping("/agregar/ejercicio")
    @PreAuthorize("hasRole('ADMIN')")
    public void agregarEjercicio(EjercicioRutina ejercicioRutina){
        rutinaServicio.agregarEjercicio(ejercicioRutina);
    }

    @DeleteMapping("/eliminar/ejercicio")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarEjercicio(long id_ejercicio){
        try{
            rutinaServicio.eliminarEjercicio(id_ejercicio);
        }catch(EjercicioNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/buscar/ejercicio")
    @PreAuthorize("hasRole('ADMIN')")
    public EjercicioRutina buscarEjercicioPorID(long id){
        return rutinaServicio.buscarEjercicioID(id);
    }

    @GetMapping("/listar/ejercicio")
    @PreAuthorize("hasRole('ADMIN')")
    public void listarRutinas(){
        System.out.println(rutinaServicio.listarEjercicio());
    }
}
