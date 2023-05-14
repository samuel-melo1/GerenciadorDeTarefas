package com.Tarefas.Gerenciador.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Tarefas.Gerenciador.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByEmail(String email);
    Optional<Usuarios> findByEmailAndSenha(String email, String senha);
}
