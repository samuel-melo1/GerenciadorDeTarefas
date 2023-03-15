package com.Tarefas.Gerenciador.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.repository.TarefasRepository;

import jakarta.validation.Valid;

public class TarefasService {

    private TarefasRepository tarefasRepository;

    TarefasService(TarefasRepository tarefasRepository){
        this.tarefasRepository = tarefasRepository;
    }

    public Tarefas criarTarefa(@Valid Tarefas tarefas){
        return tarefasRepository.save(tarefas);
        
    }
    public List<Tarefas> listarTarefas(){
        return tarefasRepository.findAll();
        
    }
    public Tarefas buscarTarefas(UUID id){
        Optional<Tarefas> tarefas = tarefasRepository.findById(id);
        if(!tarefas.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não foi encontrada");
        }
        return tarefas.get();

    }
    
}
