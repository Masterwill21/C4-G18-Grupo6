package grupo6.nomina.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import grupo6.nomina.exceptions.CustomException;
import grupo6.nomina.models.UsuarioModel;
import grupo6.nomina.services.UsuarioService;
import grupo6.nomina.utils.Autorizacion;
import grupo6.nomina.utils.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Definimos el método para agregar un usuario
    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody UsuarioModel usuario, Errors error) {
        // Verificamos si existe un error
        if (error.hasErrors()) {
            throwError(error);
        }

        Map<String, String> respuesta = new HashMap<>();

        // Ciframos la contraseña con la clase BCrypt
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));

        UsuarioModel u = this.usuarioService.buscarUsername(usuario.getUsername());
        if (u.getId() == null) {
            this.usuarioService.guardarUsuario(usuario);
            respuesta.put("mensaje", "Se agregó correctamente el usuario");

        } else {
            respuesta.put("mensaje", "El usuario ya esta registrado");

        }

        return ResponseEntity.ok(respuesta);
    }

    // Crear método para el login de usuario
    @PostMapping("/usuarios/login")
    public ResponseEntity<Map<String, String>> acceder(@RequestBody UsuarioModel usuario) {

        // Crear un objeto auxiliar del tipo usuarioModel
        UsuarioModel auxiliar = this.usuarioService.buscarUsername(usuario.getUsername());

        // Crear Map para el mensaje
        Map<String, String> respuesta = new HashMap<>();

        // Condiciones de acceso
        // Que el username no este vacío
        if (auxiliar.getUsername() == null) {
            respuesta.put("mensaje", "Usuario o contraseña incorrectos");
        } else {
            // Si las contraseñas son diferentes iguales
            if (!BCrypt.checkpw(usuario.getPassword(), auxiliar.getPassword())) {
                respuesta.put("mensaje", "Usuario o contraseña incorrectos");
            } else {
                respuesta.put("mensaje", "Se accedió correctamente");
                String hash = "";
                Long tiempo = System.currentTimeMillis();

                if (auxiliar.getId() != "") {
                     hash= Jwts.builder()
                                .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                                .setSubject(auxiliar.getNombre())
                                .setIssuedAt(new Date(tiempo))
                                .setExpiration(new Date(tiempo + 900000))
                                .claim("username", auxiliar.getUsername())
                                .compact();
                }

                auxiliar.setHash(hash);
                respuesta.put("hash", hash);
            }
        }
        return ResponseEntity.ok(respuesta);
    }

    // Método para el manejo de errores
    public void throwError(Errors error) {
        String mensaje = "";
        int index = 0;
        for (ObjectError e : error.getAllErrors()) {
            if (index > 0) {
                mensaje += " | ";
            }
            mensaje += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(), e.getDefaultMessage());
        }
        throw new CustomException(mensaje);
    }

}
