package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.EjercicioRutina;
import com.gimnasio.demo.Model.Rutina;
import com.gimnasio.demo.Service.RutinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutina")
public class RutinaController {

    @Autowired
    private RutinaServicio rutinaServicio;

    @GetMapping
    public List<Rutina> listarRutinas(){
        return rutinaServicio.listarRutinas();
    }


    public List<EjercicioRutina> listarRutinasYejercicios(){
        return rutinaServicio.listarEjercicio();
    }


}
