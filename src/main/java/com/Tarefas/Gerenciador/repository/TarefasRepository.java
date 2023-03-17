package com.Tarefas.Gerenciador.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tarefas.Gerenciador.model.Tarefas;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

    boolean existsByTitulo(String Titulo);
    boolean existsByDescricao(String Descricao);
    
    
}
