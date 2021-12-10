package com.grupo6.nomina.repositories;

import com.grupo6.nomina.models.EmpleadoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoRepository extends MongoRepository<EmpleadoModel,String> {
    
}
