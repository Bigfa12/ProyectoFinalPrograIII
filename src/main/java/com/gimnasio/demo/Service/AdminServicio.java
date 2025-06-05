package com.gimnasio.demo.Service;

import com.gimnasio.demo.DTO.UsuarioRegistroDTO;
import com.gimnasio.demo.Model.Rutina;
import com.gimnasio.demo.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServicio {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private RutinaServicio rutinaServicio;

    /// LISTADO Y ABM DE USUARIOS
    public List<Usuario> listarUsuarios()
    {
        return usuarioServicio.listarUsuarios();
    }

    public void altaUsuario(UsuarioRegistroDTO dto)
    {
        usuarioServicio.crearUsuario(dto);
    }

    public void eliminarUsuario(long id)
    {
        usuarioServicio.eliminarUsuarioPorID(id);
    }

    public void modificarUsuario(long id, Usuario usuario)
    {
        usuarioServicio.editarUsuario(id, usuario);
    }

    /// LISTADO Y ABM DE RUTINAS
    public List<Rutina> listarRutinas()
    {
        return rutinaServicio.listarRutinas();
    }

    public void altaRutina(Rutina rutina)
    {
        rutinaServicio.agregarRutina(rutina);
    }

    public void eliminarRutina(long id)
    {
        rutinaServicio.eliminarRutina(id);
    }

    public void modificarRutina(long id, Rutina rutina)
    {
        rutinaServicio.modificarRutina(id, rutina);
    }
}
