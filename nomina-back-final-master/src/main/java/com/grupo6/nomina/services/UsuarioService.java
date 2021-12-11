package com.grupo6.nomina.services;

import java.util.List;
import java.util.Optional;

import com.grupo6.nomina.models.UsuarioModel;
import com.grupo6.nomina.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Se define la clase como un servicio
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;


    //Método para guardar los datos del usuario
    public void guardar(UsuarioModel usuario) {
        this.usuarioRepository.save(usuario);
    }

    //Método para consultar todos los usuarios
    public List<UsuarioModel> traerUsuarios() {
        return this.usuarioRepository.findAll();
    }

    //Método buscar un usuario por ID
    public Optional<UsuarioModel> buscarPorId(String id){
        return this.usuarioRepository.findById(id);
    }

    //método para buscar usuario por username
    public UsuarioModel buscarPorusername(String username){
       return this.usuarioRepository.findByUsername(username).orElse(new UsuarioModel());
    }


}
