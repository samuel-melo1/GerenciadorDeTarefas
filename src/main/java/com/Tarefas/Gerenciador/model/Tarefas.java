package com.Tarefas.Gerenciador.model;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefas implements Serializable {
    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tarefa;

    private String titulo;
    private String descricao; 
    private LocalDate data_inicio;
    private LocalDate prazo;
    private String status;
    private String prioridade;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;



    
}
