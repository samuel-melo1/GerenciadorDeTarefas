package com.Tarefas.Gerenciador.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TarefasDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String titulo;
    @NotBlank
    @Size(min = 5, max = 20)
    private String descricao;
    @NotBlank
    private LocalDate data_inicio; 


}
