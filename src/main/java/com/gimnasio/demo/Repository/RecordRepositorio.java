package com.gimnasio.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepositorio extends JpaRepository<Record, Long> {
}
