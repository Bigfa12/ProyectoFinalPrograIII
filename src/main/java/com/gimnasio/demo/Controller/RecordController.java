package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.RecordAltaDTO;
import com.gimnasio.demo.DTO.RecordDTO;
import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Service.RecordServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/records")
public class RecordController {

    @Autowired
    private RecordServicio recordServicio;

    @GetMapping("/top10")
    public Map<Ejercicio, List<RecordDTO>> obtenerTop10PorEjercicio()
    {
        /// cuando la clave es enum, es mejor usar enumMap
            Map<Ejercicio, List<RecordDTO>> records=new EnumMap<>(Ejercicio.class);

            records.put(Ejercicio.PressBanca, recordServicio.obtenerTop10PorEjercicio(Ejercicio.PressBanca));
            records.put(Ejercicio.PesoMuerto, recordServicio.obtenerTop10PorEjercicio(Ejercicio.PesoMuerto));
            records.put(Ejercicio.Sentadilla, recordServicio.obtenerTop10PorEjercicio(Ejercicio.Sentadilla));
            return records;

    }

    @GetMapping("/ejercicio/{ejercicio}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RecordDTO> verRecordsPorEjercicio(@PathVariable Ejercicio ejercicio) {
        return recordServicio.verRecords(ejercicio);
    }

    @PostMapping("/altaRecord")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> altaRecord(@RequestBody RecordAltaDTO record){
        boolean esRecord = recordServicio.altaRecord(record);
        System.out.println(esRecord);
        Record record1 = recordServicio.DTOaRecord(record);

        if (esRecord){
            return ResponseEntity.ok("Record cargado con exito.");
        }
        else if (record1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente ingresado no existe.");
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El cliente ya tiene un record mayor al ingresado.");
        }

    }


}
