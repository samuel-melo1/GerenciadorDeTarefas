package com.Tarefas.Gerenciador.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tarefas.Gerenciador.model.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, UUID>{
    
}
