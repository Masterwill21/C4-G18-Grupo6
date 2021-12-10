package com.grupo6.nomina.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.grupo6.nomina.models.EmpleadoModel;
import com.grupo6.nomina.services.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Permitir origen de petición
@RequestMapping("/api")
public class EmpleadoController {
    
    @Autowired //Instancia del servicio 
    EmpleadoService empleadoService;


    /*
    Definimos el método HTTP (POST) por el que se va a ejecutar el metodo
    dentro del controller
    */
    //        http://localhost:8080/api/empleados   GET,POST,PUT,DELETE

    //---------------------------------------------------------------------
    @PostMapping("/empleados") 
    public ResponseEntity<Map<String, String>> guardarEmpleado(@Valid @RequestBody EmpleadoModel empleado){
      this.empleadoService.guardarEmpleados(empleado);
      //Mostrar un mensaje personalizado al cliente
      Map<String, String> respuesta = new HashMap<>();
      respuesta.put("mensaje","Se añadió correctamente");
      respuesta.put("estado","true");
      /*
      {
          "mensaje":"Se añadió correctamente",
          "estado":"true"
      }
      */
      return ResponseEntity.ok(respuesta);

    }
    //-------------------------------------------------------------------------





    @GetMapping("/empleados") //GET
    public List<EmpleadoModel> traerEmpleados(){
        return this.empleadoService.traerEmpleados();
    }


    @GetMapping("/empleados/{id}") //GET
    public EmpleadoModel traerEmpleadoPorId(@PathVariable String id){
        return this.empleadoService.buscarPorId(id).get();
    }
    

}
