package com.Tarefas.Gerenciador.model;

import java.time.LocalDate;
import java.util.UUID;

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
    private UUID id_tarefa;

    private String titulo;
    private String descricao;
    private LocalDate data_inicio;
    private LocalDate prazo;
    private String Status;

    
}
