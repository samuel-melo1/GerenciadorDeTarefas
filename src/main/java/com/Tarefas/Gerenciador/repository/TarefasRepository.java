package com.Tarefas.Gerenciador.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;


public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

    Optional<Tarefas> existsByTitulo(String Titulo);
    Optional<Tarefas> existsByDescricao(String Descricao);
    List<Tarefas> findByUsuarios(Usuarios usuarios);
}
