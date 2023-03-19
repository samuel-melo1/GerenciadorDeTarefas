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
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;


    
}
