package com.Tarefas.Gerenciador.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getData_inicio() {
        return data_inicio;
    }
    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }
    public String getPrazo() {
        return prazo;
    }
    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}
