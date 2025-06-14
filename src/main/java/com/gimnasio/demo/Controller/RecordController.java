package com.gimnasio.demo.Controller;

import com.gimnasio.demo.DTO.RecordDTO;
import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Service.RecordServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/records")
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

    public List<Record> verRecordsPorEjercicio(Ejercicio ejercicio) {
        return recordServicio.verRecords(ejercicio);
    }

    public void altaRecord(Record record){
        recordServicio.altaRecord(record);
    }



}
