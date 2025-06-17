package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.RecordDTO;
import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Repository.RecordRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServicio {

    @Autowired
    private RecordRepositorio recordRepositorio;

    public List<RecordDTO> obtenerTop10PorEjercicio(Ejercicio ejercicio) {
        List<Record> records = recordRepositorio.findByEjercicioOrderByPesoDesc(ejercicio);
        List<RecordDTO> top10 = new ArrayList<>();

        int cont = 0;

        for (Record r : records) {
            if (cont < 10) {
                top10.add(new RecordDTO(r.getCliente().getUsuario().getNombre(),
                        r.getCliente().getUsuario().getApellido(),
                        r.getPeso(),
                        r.getEjercicio()
                ));
            }
            cont++;
        }
        return top10;
    }

    public List<Record> verRecords(Ejercicio ejercicio) {
        return recordRepositorio.findByEjercicioOrderByPesoDesc(ejercicio);
    }

    public void altaRecord(Record record){
        recordRepositorio.save(record);
    }




}
