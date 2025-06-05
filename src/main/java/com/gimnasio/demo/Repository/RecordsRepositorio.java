package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepositorio extends JpaRepository<Records, Long> {
}
