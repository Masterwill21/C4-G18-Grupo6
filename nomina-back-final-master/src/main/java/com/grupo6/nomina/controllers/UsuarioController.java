package com.grupo6.nomina.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.grupo6.nomina.models.UsuarioModel;

import com.grupo6.nomina.services.UsuarioService;
import com.grupo6.nomina.utils.Autorizacion;
import com.grupo6.nomina.utils.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*") // Permitir origen de petición
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

   /* *
   Crear un método get para verificar la validez del token
   */
  @GetMapping("/verificar") //Ruta para acceder al método
  public ResponseEntity<Map<String, Boolean>> verificarToken(){ // "{"ok": true}"
      Map<String, Boolean> respuesta = new HashMap<>();
      respuesta.put("ok",true);
      return ResponseEntity.ok(respuesta);
  }

    //Método para registrar a un usuario
    //Definimos el método de la petición HTTP Post

    @PostMapping("/usuarios") //POST 
    public ResponseEntity<Map<String, String>> guardarUsuarios(@RequestBody UsuarioModel usuario){
        
        //Map tener una clave valor {"mensaje": "Se agregó correctamente"}
        Map<String, String> respuesta= new HashMap<>();
        
        //Codificar contraseña
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
       
       
        UsuarioModel u=this.usuarioService.buscarPorusername(usuario.getUsername());
        if(u.getId()==null){
            this.usuarioService.guardar(usuario);
            respuesta.put("mensaje","Se agregó correctamente");
        }else{
            respuesta.put("mensaje","Usuario ya existe");
        }

        return ResponseEntity.ok(respuesta);
    }


    //Método para chekear autenticidad
    @PostMapping("/usuarios/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UsuarioModel usuario){
        UsuarioModel usuarioAux=this.usuarioService.buscarPorusername(usuario.getUsername());
        Map<String, String> respuesta = new HashMap<>();
        if(usuarioAux.getUsername()==null){
            respuesta.put("mensaje","usuario o contraseña incorrectos");
        }else{
            if(!BCrypt.checkpw(usuario.getPassword(), usuarioAux.getPassword())){
                respuesta.put("mensaje","usuario o contraseña incorrectos");
            }else{
                String hash ="";
                long tiempo = System.currentTimeMillis();
                if(usuarioAux.getId()!=null){
                    hash=Jwts.builder()
                           .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                           .setSubject(usuarioAux.getNombre())
                           .setIssuedAt(new Date(tiempo))
                           .setExpiration(new Date(tiempo+900000))
                           .claim("username", usuarioAux.getUsername())
                           .claim("correo", usuarioAux.getCorreo())
                           .compact();
                }
                usuarioAux.setHash(hash);
                respuesta.put("mensaje","Accedió correctamente");
                respuesta.put("token",hash);
                respuesta.put("id",usuarioAux.getId());
                respuesta.put("nombre",usuarioAux.getNombre());
                respuesta.put("correo",usuarioAux.getCorreo());
                respuesta.put("username",usuarioAux.getUsername());
            }
        }
        return ResponseEntity.ok(respuesta);
    }







    @PutMapping("/usuarios") //POST 
    public ResponseEntity<Map<String, String>> actualizarUsuario(@RequestBody UsuarioModel usuario){
        
        //Map tener una clave valor {"mensaje": "Se agregó correctamente"}
        Map<String, String> respuesta= new HashMap<>();
            this.usuarioService.guardar(usuario); //Actualizo al usuario
            respuesta.put("mensaje","Se actualizó correctamente");

            //Busco el partdio que contenga a ese usuario
           // NominaModel parAux=this.nominaService.getNomina("61945de3105cd33b0f98d96a");
            //Modifico la información de usuario dentro del nomina
        //    parAux.getUsuario().setNombre(usuario.getNombre());
            //y se le agrega al nomina
        //    actualizarNomina(parAux);
        return ResponseEntity.ok(respuesta);
    }

/*
    public void actualizarNomina(NominaModel nomina){
        this.nominaService.guardarNomina(nomina);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje","Se actualizó correctamente");
    }*/

    
}
