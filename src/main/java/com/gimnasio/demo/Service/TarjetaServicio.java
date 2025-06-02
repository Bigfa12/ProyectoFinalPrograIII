package com.gimnasio.demo.Service;

import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
@Service

public class TarjetaServicio {
    @Autowired
    TarjetaRepositorio tarjetaRepositorio;
=======
import org.springframework.stereotype.Service;

@Service
public class TarjetaServicio {
    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;

>>>>>>> 2bf7f664fc86b58398fd9e081db3b41737f68284

}
