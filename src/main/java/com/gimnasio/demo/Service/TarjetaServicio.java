package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.TarjetaIngresoDTO;
import com.gimnasio.demo.Exceptions.TarjetaNoEncontradaException;
import com.gimnasio.demo.Exceptions.UsuarioNoEncontradoException;
import com.gimnasio.demo.Model.Tarjeta;
import com.gimnasio.demo.Model.User;
import com.gimnasio.demo.Model.Usuario;
import com.gimnasio.demo.Repository.TarjetaRepositorio;
import com.gimnasio.demo.Repository.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class TarjetaServicio {
    @Autowired
    private TarjetaRepositorio tarjetaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Tarjeta conversorDTO(TarjetaIngresoDTO Dto){
        Tarjeta tarjeta = new Tarjeta(Dto.getNroTrajeta(),Dto.getNombreTitular(),Dto.getFechaVencimiento(),Dto.getDni());
        return tarjeta;
    }

    public boolean ingresarTarjeta (TarjetaIngresoDTO Dto, Usuario usuario){

        Tarjeta tarjeta = conversorDTO(Dto);
        boolean existe = false;
        List<Tarjeta> tarjetas = usuario.getTarjetas();
        for(Tarjeta t: tarjetas){
            if(t.getNroTarjeta().equals(tarjeta.getNroTarjeta())){
                existe = true;
            }
        }
        if (!existe){
            tarjeta.setUsuario(usuario);
            tarjetaRepositorio.save(tarjeta);
        }

        return existe;
    }

    public boolean eliminarTarjeta(long id){
        boolean existe = true;

        if(tarjetaRepositorio.existsById(id)){

            tarjetaRepositorio.deleteById(id);
        }else{
            existe =  false;
        }
        return existe;
    }

}
