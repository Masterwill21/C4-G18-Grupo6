package grupo6.nomina.services;

import java.util.List;

import grupo6.nomina.models.EmpleadoModel;
import grupo6.nomina.repositories.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    
    @Autowired //Instancia del repositorio
    EmpleadoRepository empleadoRepository; //Variable que hace referencia al repositorio

    //Metodo para guardar datos del empleado
    public void guardarEmpleado(EmpleadoModel empleado){
        this.empleadoRepository.save(empleado);
    }

    //Método para listar los empleados
    public List<EmpleadoModel> obtenerEmpleados(){
        return this.empleadoRepository.findAll();
    }
}
