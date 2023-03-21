package com.Tarefas.Gerenciador.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios {

    @Id
    private long id_usuario;

    private String username;
    private String password;
    private String email;


    @ManyToMany
    @JoinTable(
        name =  "tarefasusuarios",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_tarefa"))
    private Set<Tarefas> tarefas = new HashSet<>();

    
}
