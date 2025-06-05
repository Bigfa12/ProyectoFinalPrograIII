package com.gimnasio.demo.Controller;

import com.gimnasio.demo.Model.Records;
import com.gimnasio.demo.Service.RecordsServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordsController {

    @Autowired
    private RecordsServicio recordsServicio;

    @PostMapping
    public ResponseEntity<String> insertRecords(@RequestBody Records records) {
        recordsServicio.insertarRecord(records);
        return ResponseEntity.ok("Record Insertado Correctamente.");
    }

    @GetMapping
    public ResponseEntity<List<Records>> getRecords() {
        if (!recordsServicio.listarRecords().isEmpty()) {
            return ResponseEntity.ok(recordsServicio.listarRecords());
        }else{
            return ResponseEntity.notFound().build();//404 not found
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Records> getRecordsById(@RequestParam long id) {
        if (recordsServicio.buscarRecord(id) != null) {
            return ResponseEntity.ok(recordsServicio.buscarRecord(id));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRecordsById(@RequestParam long id) {
        if (recordsServicio.buscarRecord(id) != null) {
            recordsServicio.eliminarRecord(id);
            return ResponseEntity.ok("Record Eliminado Correctamente.");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updateRecordsById(@RequestParam long id, @RequestBody Records records) {
        if (recordsServicio.buscarRecord(id) != null) {
            records.setId_record(id);
            recordsServicio.modificarRecord(records);
            return ResponseEntity.ok("Record Actualizado Correctamente.");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
