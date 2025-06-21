package com.gimnasio.demo.Service;

import com.gimnasio.demo.Exceptions.EjercicioNoEncontradoException;
import com.gimnasio.demo.Exceptions.RutinaNoEncontradaException;
import com.gimnasio.demo.Model.EjercicioRutina;
import com.gimnasio.demo.Model.Rutina;
import com.gimnasio.demo.Repository.EjercicioRutinaRepositorio;
import com.gimnasio.demo.Repository.RutinaRepositorio;
import jakarta.transaction.Transactional;
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

    public void agregarRutina(Rutina rutina){
        if (!rutinaRepositorio.existsByDia(rutina.getDia())){
            rutinaRepositorio.save(rutina);

            for (EjercicioRutina ejercicioRutina : rutina.getEjercicios()) {
                ejercicioRutina.setRutina(rutina);
                ejercicioRutinaRepositorio.save(ejercicioRutina);
            }
        }
        else{
            List<Rutina> existentes = rutinaRepositorio.findAllByDia(rutina.getDia());
            for (Rutina existente : existentes) {
                rutinaRepositorio.delete(existente);
            }

           
            rutinaRepositorio.save(rutina);
            for (EjercicioRutina ejercicioRutina : rutina.getEjercicios()) {
                ejercicioRutina.setRutina(rutina);
                ejercicioRutinaRepositorio.save(ejercicioRutina);
            }
        }

    }

    public void eliminarRutina(long id_rutina) throws RutinaNoEncontradaException
    {
        if(rutinaRepositorio.existsById(id_rutina)){
            rutinaRepositorio.deleteById(id_rutina);
        }else{
            throw new RutinaNoEncontradaException("esa rutina no existe");
        }

    }

    public void modificarRutina(long id_rutina, Rutina rutina) throws RutinaNoEncontradaException
    {
        if(rutinaRepositorio.existsById(id_rutina))
        {
            rutinaRepositorio.save(rutina);
        }else{
            throw new RutinaNoEncontradaException("esa rutina no existe");
        }
    }

    public Rutina buscarRutinaID(long id_rutina){
        Optional<Rutina> rutina = rutinaRepositorio.findById(id_rutina);
        return rutina.orElse(null);
    }


    //EjercicioRutina

    public void agregarEjercicio(EjercicioRutina ejercicio,Long rutinaID){
            Rutina rutina = rutinaRepositorio.findById(rutinaID).orElseThrow(()->new RutinaNoEncontradaException("esa rutina no existe"));
            ejercicio.setRutina(rutina);
        ejercicioRutinaRepositorio.save(ejercicio);
    }

    public void eliminarEjercicio(Long id_ejercicio) throws EjercicioNoEncontradoException{
        if(ejercicioRutinaRepositorio.existsById(id_ejercicio)){
            ejercicioRutinaRepositorio.deleteById(id_ejercicio);
        }else{
            throw new EjercicioNoEncontradoException("ese ejercicio no existe");
        }
    }

    public EjercicioRutina buscarEjercicioID(long id_ejercicio){
        Optional<EjercicioRutina> ejercicio = ejercicioRutinaRepositorio.findById(id_ejercicio);
        return ejercicio.orElse(null);
    }

    public List<EjercicioRutina> listarEjercicio(){
        return ejercicioRutinaRepositorio.findAll();
    }
}
