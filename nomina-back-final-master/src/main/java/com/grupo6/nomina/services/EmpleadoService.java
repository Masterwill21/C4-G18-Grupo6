package com.grupo6.nomina.services;

import java.util.List;
import java.util.Optional;

import com.grupo6.nomina.models.EmpleadoModel;
import com.grupo6.nomina.repositories.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Definimos la clase como un servicio
public class EmpleadoService {

    @Autowired //Creamos la instancia del repository
    EmpleadoRepository empleadoRepository; 

    //Método para guardar empleados
    public void guardarEmpleados(EmpleadoModel empleado) {
        this.empleadoRepository.save(empleado);
    }

    //Método para traer los Empleados
    public List<EmpleadoModel> traerEmpleados() {
        return this.empleadoRepository.findAll();
    }

    //Método para buscar empleado por ID
    public Optional<EmpleadoModel> buscarPorId(String id) {
         return this.empleadoRepository.findById(id);
    }

    //Método para verificar si ya existe el empleado
    public Boolean verificar(String id) {
        return this.empleadoRepository.existsById(id);
    }

    //Método para eliminar un empleado
    public void eliminar(String id) {
        this.empleadoRepository.deleteById(id);
    }

    
}
