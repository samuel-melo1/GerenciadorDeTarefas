package com.Tarefas.Gerenciador.dto;
import java.time.LocalDate;

import com.Tarefas.Gerenciador.configs.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefasDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "campo deve ser entre 3 e 20 ok")
    private String titulo;
    @NotBlank
    @Size(min = 5, max = 20)
    private String descricao;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate data_inicio; 
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate prazo;
    @NotBlank
    private String status;
    @NotBlank
    private String prioridade;
 


}
