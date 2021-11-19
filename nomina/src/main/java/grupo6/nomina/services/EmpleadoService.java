package grupo6.nomina.services;
import java.util.List;

import com.grupo6.nomina.models.EmpleadoModel;
import com.grupo6.nomina.repositories.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    @Autowired //Instancia del repositorio
    EmpleadoRepository EmpleadoRepository; //Variable que hace referencia al repositorio

    //Metodo para guardar datos del Empleado
    public void guardarEmpleado(EmpleadoModel Empleado){
        this.EmpleadoRepository.save(Empleado);
    }

    //Método para listar los Empleados
    public List<EmpleadoModel> obtenerEmpleados(){
        return this.EmpleadoRepository.findAll();
    }
}
