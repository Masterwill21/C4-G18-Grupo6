package grupo6.nomina.repositories;

import grupo6.nomina.models.EmpleadoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository //Etiqueta que asígna la interfaz como repositorio
public interface EmpleadoRepository extends MongoRepository<EmpleadoModel,String>{
    /* 
    La Clase MongoRepository contiene creados 
    métodos de las funcione básica del CRUD
    C - Crear o agregar datos
    R - Leer datos 
    U - Actualizar
    D - Eliminar datos
    */

}
