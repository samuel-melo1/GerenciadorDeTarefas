package com.Tarefas.Gerenciador.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long>{

    Optional<Tarefas> existsByTitulo(String Titulo);
    Optional<Tarefas> existsByDescricao(String Descricao);
    List<Tarefas> findByUsuarios(Usuarios usuarios);
  
}
