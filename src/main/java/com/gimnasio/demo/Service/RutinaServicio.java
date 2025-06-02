package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.EjercicioRutina;
import com.gimnasio.demo.Model.Rutina;
import com.gimnasio.demo.Repository.EjercicioRutinaRepositorio;
import com.gimnasio.demo.Repository.RutinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaServicio {

    @Autowired
    private RutinaRepositorio rutinaRepositorio;

    @Autowired
    private EjercicioRutinaRepositorio ejercicioRutinaRepositorio;

    //Rutina

    public Rutina agregarRutina(Rutina rutina){
        rutinaRepositorio.save(rutina);
        return rutina;
    }

    public void eliminarRutina(long id_rutina){
        rutinaRepositorio.deleteById(id_rutina);
    }

    public Rutina buscarRutinaID(long id_rutina){
        Optional<Rutina> rutina = rutinaRepositorio.findById(id_rutina);
        return rutina.orElse(null);
    }

    public List<Rutina> listarRutinas(){
        return rutinaRepositorio.findAll();
    }

    //EjercicioRutina

    public EjercicioRutina agregarEjercicio(EjercicioRutina ejercicio){
        ejercicioRutinaRepositorio.save(ejercicio);
        return ejercicio;
    }

    public void eliminarEjercicio(long id_ejercicio){
        ejercicioRutinaRepositorio.deleteById(id_ejercicio);
    }

    public EjercicioRutina buscarEjercicioID(long id_ejercicio){
        Optional<EjercicioRutina> ejercicio = ejercicioRutinaRepositorio.findById(id_ejercicio);
        return ejercicio.orElse(null);
    }

    public List<EjercicioRutina> listarEjercicio(){
        return ejercicioRutinaRepositorio.findAll();
    }

}
