package com.Tarefas.Gerenciador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtribuirTarefaDTO {

    private Long id_usuario;
    private Long id_tarefa;
}
