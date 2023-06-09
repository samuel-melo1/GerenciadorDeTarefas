package com.Tarefas.Gerenciador.model;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data_inicio;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate prazo;
    private String status;
    private String prioridade;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;
}
