package com.grupo6.nomina.utils;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class Autorizacion implements Filter {
    
    //Clave para codifica y decodificar
    public static final String KEY="dndsjkfjksddsfdfgfdbf";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       
                //Obtener el path principal 
                HttpServletRequest httpServletRequest=(HttpServletRequest) request;
                
                HttpServletResponse httpServletResponse=(HttpServletResponse) response;
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");


                // http://localhost:8080/
                String url =httpServletRequest.getRequestURI();
                if(url.contains("/api/usuarios")||url.contains("/api/usuarios/login")||url.contains("index")){
                    chain.doFilter(request, response); 
                }else{
                    String hash=httpServletRequest.getHeader("Authorization");
                    if(hash==null || hash.trim().equals("")){
                        response.setContentType("application/json");
                        String body="{\"autorizacion\":\"NO\"}";
                        response.getWriter().write(body);
                    }
                    try {
                        //Rutas privadas (Solo con Token)
                        Jws<Claims> claims=Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                        if((url.contains("/api/verificar")||url.contains("/api/empleados")||url.contains("/api/nominas"))&&(!claims.getBody().get("username").equals(""))){
                            chain.doFilter(request, response);    
                        }

                    } catch (Exception e) {
                        response.setContentType("application/json");
                        String body="{\"autorizacion\":\"TOKEN NO VALIDO\"}";
                        response.getWriter().write(body);
                    }
                }


        
    }

   

}
