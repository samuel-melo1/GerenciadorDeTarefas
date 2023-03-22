package com.Tarefas.Gerenciador.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tarefas.Gerenciador.dto.AtribuirTarefaDTO;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.service.AtribuicaoTarefaService;

@RestController
@RequestMapping("/atribuicoes")
public class AtribuicaoTarefaController {

    private AtribuicaoTarefaService atribuicaoTarefaService;

    AtribuicaoTarefaController(AtribuicaoTarefaService atribuicaoTarefaService){
        this.atribuicaoTarefaService = atribuicaoTarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefas> atribuirTarefa(@RequestBody AtribuirTarefaDTO atribuirTarefaDTO){

        Optional<Tarefas> optionalTarefa = atribuicaoTarefaService.atribuirTarefa(atribuirTarefaDTO);

        if(optionalTarefa.isPresent()){
            return ResponseEntity.ok(optionalTarefa.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    
}
