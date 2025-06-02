package com.gimnasio.demo.Service;

import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TarjetaServicio {
    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;



}
