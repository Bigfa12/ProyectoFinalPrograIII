package com.gimnasio.demo.Service;

import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
@Service

public class TarjetaServicio {
    @Autowired
    TarjetaRepositorio tarjetaRepositorio;

}
