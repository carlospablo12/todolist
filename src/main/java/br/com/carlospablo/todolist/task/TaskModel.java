package br.com.carlospablo.todolist.task;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
    @Data
    @Entity(name = "tb_tasks")

public class TaskModel {
    // Id
    // Usuário (ID_USUARIO)
    // Descrição
    // Titulo
    // Dt Inicio
    // Dt Termino
    // Prioridade

    @Id
    @GeneratedValue(generator = "UUID")
    private  UUID id;
    private UUID idUser;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("O campo titulo deve conter no máximo 50 caractes");
        }
        this.title = title;
    }
   

}
