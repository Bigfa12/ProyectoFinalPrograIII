package com.gimnasio.demo.Service;

import com.gimnasio.demo.Model.Credencial;
import com.gimnasio.demo.Repository.CredencialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CredencialServicio {
    @Autowired
    CredencialRepositorio credencialRepositorio;

}
