package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.RecordDTO;
import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Exceptions.RecordNoEncontradoException;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Service.RecordServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin/records")
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
    public List<Record> verRecordsPorEjercicio(@PathVariable Ejercicio ejercicio) {
        return recordServicio.verRecords(ejercicio);
    }

    @PostMapping("/altaRecord")
    @PreAuthorize("hasRole('ADMIN')")
    public void altaRecord(@RequestBody Record record){
        recordServicio.altaRecord(record);
    }

    @DeleteMapping("/bajaRecord/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void bajaRecord(@PathVariable long id){
        try{
            recordServicio.bajaRecord(id);
        }catch (RecordNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }
}
