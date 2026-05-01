package br.com.carlospablo.todolist.filter;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import br.com.carlospablo.todolist.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    
   

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
                var servletPath = request.getServletPath();
                if(servletPath.startsWith("/tasks/")){
                    
                    // Pegar a autenticação do usuario e senha
                    var authorization = request.getHeader("Authorization");
                    var token = authorization.substring("Bearer ".length()).trim();
                    
    
                    // Validar Usuário 
                   var isValid =  jwtService.validateToken(token);
                   if(!isValid){
                    response.sendError(401, "Token Inválido");
                    return;
                   }

                  var idUser = jwtService.extractSubject(token);
                  request.setAttribute("idUser", UUID.fromString(idUser)); 
                   filterChain.doFilter(request, response);                      
        
                }else{
                        filterChain.doFilter(request, response);
                   }
    
                
}}
