package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.Records;
import com.gimnasio.demo.Repository.RecordsRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordsServicio {

    @Autowired
    private RecordsRepositorio recordRepositorio;

    public void insertarRecord(Records r) {
        recordRepositorio.save(r);
    }

    public void modificarRecord(Records r) {
        recordRepositorio.save(r);
    }

    public void eliminarRecord(long id) {
        recordRepositorio.deleteById(id);
    }

    public Records buscarRecord(long id) {
        Optional<Records> r = recordRepositorio.findById(id);
        if (r.isPresent()) {
            return r.get();
        } else return null;
    }

    public List<Records> listarRecords() {
        return recordRepositorio.findAll();
    }

}
