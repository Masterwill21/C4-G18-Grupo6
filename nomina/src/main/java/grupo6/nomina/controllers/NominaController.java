package grupo6.nomina.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import grupo6.nomina.exceptions.CustomException;
import grupo6.nomina.models.NominaModel;
import grupo6.nomina.services.NominaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NominaController {
    
    @Autowired
    NominaService service;


    @PostMapping("/nominas") //Método HTTP -------> POST
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody NominaModel nomina, Errors error){
        //Verificamos si existe un error
        if(error.hasErrors()){
            throwError(error);
        }
        //Recibimos los datos por el body de la petición
        this.service.guardarNomina(nomina); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se agregó el nomina correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
    }

    @GetMapping("/nominas") //Método HTTP -------> GET
    public List<NominaModel> mostrar(){
        return service.traerTodos();
    }

    @PutMapping("/nominas") // Método HTTP ----> PUT
    public ResponseEntity<Map<String, String>> actualizar(@Valid @RequestBody NominaModel nomina){
        //Recibimos los datos por el body de la petición
        this.service.guardarNomina(nomina); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se actualizó el nomina correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
    }

      //Método para el manejo de errores
      public void throwError(Errors error){
        String mensaje="";
        int index=0;
        for(ObjectError e: error.getAllErrors()){
            if(index>0){
                mensaje +=" | ";
            }
            mensaje+=String.format("Parametro: %s - Mensaje: %s", e.getObjectName(),e.getDefaultMessage());
        }
        throw new CustomException(mensaje);
    }
}
