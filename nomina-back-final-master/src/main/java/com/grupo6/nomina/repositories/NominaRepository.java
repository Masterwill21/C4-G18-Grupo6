package com.grupo6.nomina.repositories;

import com.grupo6.nomina.models.NominaModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominaRepository extends MongoRepository<NominaModel,String> {
    
}
