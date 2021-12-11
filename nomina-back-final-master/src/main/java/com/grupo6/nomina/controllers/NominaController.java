package com.grupo6.nomina.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grupo6.nomina.models.NominaModel;
import com.grupo6.nomina.services.NominaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

//Definimos el tipo de controlador 
@RestController
@RequestMapping("/api") //definimos la ruta
public class NominaController {
    
    //Instanciar el servicio de los nominas
    @Autowired
    NominaService nominaService;

    //Método para guardar un nomina
    @PostMapping("/nominas")
    public ResponseEntity<Map<String, String>> guardar(@RequestBody NominaModel nomina){
        this.nominaService.guardarNomina(nomina);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje","Se agregó correctamente");

        return ResponseEntity.ok(respuesta);

    }

    //Método para obtener los datos de los nominas
    @GetMapping("/nominas")
    public List<NominaModel> mostrar(){
        return this.nominaService.traerNominas();
    }

    //Método para actualizar un nomina
    @PutMapping("/nominas")
    public ResponseEntity<Map<String, String>> actualizar(@RequestBody NominaModel nomina){
        this.nominaService.guardarNomina(nomina);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje","Se actualizó correctamente");

        return ResponseEntity.ok(respuesta);

    }



    
}
