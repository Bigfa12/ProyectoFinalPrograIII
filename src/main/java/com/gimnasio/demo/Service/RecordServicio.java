package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.RecordAltaDTO;
import com.gimnasio.demo.DTO.RecordDTO;
import com.gimnasio.demo.Enums.Ejercicio;
import com.gimnasio.demo.Exceptions.RecordNoEncontradoException;
import com.gimnasio.demo.Model.Cliente;
import com.gimnasio.demo.Model.Record;
import com.gimnasio.demo.Repository.ClienteRepositorio;
import com.gimnasio.demo.Repository.RecordRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServicio {

    @Autowired
    private RecordRepositorio recordRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<RecordDTO> obtenerTop10PorEjercicio(Ejercicio ejercicio) {
        List<Record> records = recordRepositorio.findByEjercicioOrderByPesoDesc(ejercicio);
        List<RecordDTO> top10 = new ArrayList<>();

        int cont = 0;

        for (Record r : records) {
            if (cont < 10 && r.getCliente().isAlDia()) {
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

    public List<RecordDTO> verRecords(Ejercicio ejercicio) {
        List<Record> records = recordRepositorio.findByEjercicioOrderByPesoDesc(ejercicio);
        return records.stream()
                .map(RecordDTO::new)
                .collect(Collectors.toList());
    }


    public boolean altaRecord(RecordAltaDTO recordDTO){
        Record record1 = DTOaRecord(recordDTO);
        List<Record> records = recordRepositorio.findByEjercicioOrderByPesoDesc(recordDTO.getEjercicio());
        System.out.println("Lista de records" + records);
        Record recordAnterior = null;
        boolean esRecord = false;

        if (record1 != null) {
            System.out.println("ENTRO AL FOR");
            for (Record r : records) {
                if(r.getCliente().getIdCliente().equals(record1.getCliente().getIdCliente())){
                    recordAnterior = r;
                    System.out.println(recordAnterior);
                    if (recordAnterior.getPeso() < record1.getPeso()) {
                        recordRepositorio.save(record1);
                        recordRepositorio.delete(recordAnterior);
                        esRecord = true;
                    }
                }
            }
            if (recordAnterior == null) {
                System.out.println("ENTRO AL IF?");
                recordRepositorio.save(record1);
                esRecord = true;
            }
        }

        return esRecord;
    }

    public Record DTOaRecord(RecordAltaDTO recordDTO){
        Record record = new Record();
        Cliente cliente = clienteRepositorio.findByIdCliente(recordDTO.getId_cliente());

        if (cliente != null) {
            record.setCliente(cliente);
            record.setPeso(recordDTO.getPeso());
            record.setEjercicio(recordDTO.getEjercicio());
            return record;
        }
        return null;
    }

    public void bajaRecord(long id) throws RecordNoEncontradoException {
        if(recordRepositorio.existsById(id)){
            recordRepositorio.deleteById(id);
        }
        else{
            throw new RecordNoEncontradoException("ese record no existe");
        }

    }




}
