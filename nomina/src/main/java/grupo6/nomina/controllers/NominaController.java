package grupo6.nomina.controllers;
import java.util.*;

import javax.validation.Valid;

import com.grupo6.nomina.models.NominaModel;
import com.grupo6.nomina.services.NominaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NominaController {
    @Autowired
    NominaService service;


    @PostMapping("/Nominas") //Método HTTP -------> POST
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody NominaModel Nomina){
        //Recibimos los datos por el body de la petición
        this.service.guardarNomina(Nomina); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se agregó el Nomina correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
    }

    @GetMapping("/Nominas") //Método HTTP -------> GET
    public List<NominaModel> mostrar(){
        return service.traerTodos();
    }

    @PutMapping("/Nominas") // Método HTTP ----> PUT
    public ResponseEntity<Map<String, String>> actualizar(@Valid @RequestBody NominaModel Nomina){
        //Recibimos los datos por el body de la petición
        this.service.guardarNomina(Nomina); //Invocamos el metodo creado en el servicio
        Map<String, String> respuesta=new HashMap<>();//Creamos el map para la respuesta al cliente
        respuesta.put("mensaje", "Se actualizó el Nomina correctamente"); //Se agrega la respuesta que se quiere enviar
        respuesta.put("estado", "true");
        return ResponseEntity.ok(respuesta); //Y se retorna esa respuesta
    }
}
