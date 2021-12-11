package com.grupo6.nomina.repositories;

import java.util.Optional;

import com.grupo6.nomina.models.UsuarioModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel,String>{
    //Método para buscar por nombre de usuario
    public Optional<UsuarioModel> findByUsername(String username);
}
