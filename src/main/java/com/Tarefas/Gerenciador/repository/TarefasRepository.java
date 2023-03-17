package com.Tarefas.Gerenciador.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Tarefas.Gerenciador.model.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

    boolean existsByTitulo(String Titulo);
    boolean existsByDescricao(String Descricao);
    boolean existsByData_inicio(String data_inicio);


    
}
