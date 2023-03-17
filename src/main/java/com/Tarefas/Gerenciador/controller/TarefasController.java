package com.Tarefas.Gerenciador.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tarefas.Gerenciador.dto.TarefasDto;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.service.TarefasService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private TarefasService tarefasService;
    private Tarefas tarefas;

    TarefasController(TarefasService tarefasService){
        this.tarefasService = tarefasService;
    }
    TarefasController(Tarefas tarefas){
        this.tarefas = tarefas;
    }

    @PostMapping
    public ResponseEntity<Tarefas> criarTarefa( @RequestBody @Valid TarefasDto tarefasDto){
        BeanUtils.copyProperties(tarefasDto, tarefas);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.criarTarefa(tarefas));
    }
    
    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTarefas(){
        List<Tarefas> tarefas = tarefasService.listarTarefas();
        return ResponseEntity.ok().body(tarefas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> buscarTarefa(@PathVariable Long id){
        Tarefas tarefas = tarefasService.buscarTarefas(id);
        return ResponseEntity.ok().body(tarefas);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefasDto tarefaAtualizadaDto){
        BeanUtils.copyProperties(tarefaAtualizadaDto, tarefas);
        return ResponseEntity.ok().body(tarefasService.atualizarTarefas(id, tarefas));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id){
        tarefasService.excluirTarefas(id);
        return ResponseEntity.ok().build();
    }
    
}
