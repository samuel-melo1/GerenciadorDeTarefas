package com.Tarefas.Gerenciador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tarefa;

    private String titulo;
    private String descricao;
    private String data_inicio;
    private String prazo;
    private String status;

    
}
