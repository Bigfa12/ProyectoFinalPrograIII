package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.TarjetaIngresoDTO;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TarjetaServicio {
    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;

    public Tarjeta convertidorDTO(TarjetaIngresoDTO Dto){
        Tarjeta tarjeta = new Tarjeta(Dto.getNroTrajeta(), Dto.getNombreTitular(), Dto.getFechaVencimiento(), Dto.getCvv());

        return tarjeta;
    }

    public boolean ingresarTarjeta (TarjetaIngresoDTO Dto){
        boolean b = false;
        Tarjeta tarjeta = convertidorDTO(Dto);

        if(!tarjetaRepositorio.existsByNroTarjeta(tarjeta.getNroTarjeta())){
            b=true;
            tarjetaRepositorio.save(tarjeta);
        }

        return b;
    }

    /// IMPLEMENTAR EXCEPTION/////////////////////////////////////////////////////////////////////////////////////////
    public void eliminarTarjeta(long id){
        tarjetaRepositorio.deleteById(id);
    }


}
