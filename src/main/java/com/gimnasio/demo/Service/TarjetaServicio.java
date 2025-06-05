package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.TarjetaIngresoDTO;
import com.gimnasio.demo.Exceptions.TarjetaNoEncontradaException;
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

        if(!tarjetaRepositorio.existsByNroTrajeta(tarjeta.getNroTrajeta())){
            b=true;
            tarjetaRepositorio.save(tarjeta);
        }

        return b;
    }

    public void eliminarTarjeta(long id) throws TarjetaNoEncontradaException {
        if(tarjetaRepositorio.existsById(id)){
            tarjetaRepositorio.deleteById(id);
        }else{
            throw new TarjetaNoEncontradaException("esa tarjeta no existe");
        }
    }


}
