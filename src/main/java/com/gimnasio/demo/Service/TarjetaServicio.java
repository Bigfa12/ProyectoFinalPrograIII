package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.TarjetaIngresoDTO;
import com.gimnasio.demo.Exceptions.TarjetaNoEncontradaException;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import com.gimnasio.demo.Repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component

public class TarjetaServicio {
    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Tarjeta conversorDTO(TarjetaIngresoDTO Dto){
        Tarjeta tarjeta = new Tarjeta(Dto.getNroTrajeta(),Dto.getNombreTitular(),Dto.getFechaVencimiento(),Dto.getCvv(),Dto.getDni());
        return tarjeta;
    }

    public void ingresarTarjeta (TarjetaIngresoDTO Dto, Usuario usuario){

        Tarjeta tarjeta = conversorDTO(Dto);

        if(!tarjetaRepositorio.existsByNroTarjeta(tarjeta.getNroTarjeta())){
                tarjeta.setUsuario(usuario);
                tarjetaRepositorio.save(tarjeta);
        }
    }

    public void eliminarTarjeta(long id) throws TarjetaNoEncontradaException {
        if(tarjetaRepositorio.existsById(id)){
            tarjetaRepositorio.deleteById(id);
        }else{
            throw new TarjetaNoEncontradaException("esa tarjeta no existe");
        }
    }
}
