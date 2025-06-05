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
    public ResponseEntity<List<Rutina>> listarRutinas(){
        List<Rutina> rutinas = rutinaServicio.listarRutinas();

        if (rutinas.isEmpty()){
            return ResponseEntity.notFound().build();// 404 Not Found
        }
        else{
            return ResponseEntity.ok(rutinas);
        }
    }

}
