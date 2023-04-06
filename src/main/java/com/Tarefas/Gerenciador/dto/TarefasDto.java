package com.Tarefas.Gerenciador.dto;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String data_inicio; 
    @NotBlank
    private String prazo;
    @NotBlank
    private String status;
    @NotBlank
    private String prioridade;
 


}
