package com.Tarefas.Gerenciador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarefasusuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TarefasUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tarefa")
    private Tarefas tarefas;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;
    
}
