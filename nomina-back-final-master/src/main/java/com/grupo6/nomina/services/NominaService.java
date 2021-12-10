package com.grupo6.nomina.services;

import java.util.List;


import com.grupo6.nomina.models.NominaModel;
import com.grupo6.nomina.repositories.NominaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominaService {
    

    //Crear la instancia del NominaRepository
    @Autowired
    NominaRepository nominaRepository;// contiene los métodos crud


    //Método para guaradar un nomina
    public void guardarNomina(NominaModel nomina){
       this.nominaRepository.save(nomina);
    }


    //Método para listar los nominas
    public List<NominaModel> traerNominas(){
        return this.nominaRepository.findAll();
    }

    public NominaModel getNomina(String id){
      return  this.nominaRepository.findById(id).orElse(new NominaModel());
    }




}
