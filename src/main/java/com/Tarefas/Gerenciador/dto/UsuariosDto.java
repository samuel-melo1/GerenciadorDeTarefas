package com.Tarefas.Gerenciador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String senha;
    @NotBlank
    private String email;
}
