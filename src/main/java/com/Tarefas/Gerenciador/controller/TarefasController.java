package com.Tarefas.Gerenciador.controller;

import java.util.List;
import java.util.Optional;
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
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.TarefasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private TarefasService tarefasService;

    TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @PostMapping
    public ResponseEntity<Object> criarTarefa(@RequestBody @Valid TarefasDto tarefasDto, HttpServletRequest request,
            HttpSession session) {
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefasDto, tarefas);
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        tarefas.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.criarTarefa(tarefas));
    }

    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTarefas() {
        List<Tarefas> tarefas = tarefasService.listarTarefas();
        return ResponseEntity.ok().body(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarTarefa(@PathVariable Long id) {
        Optional<Tarefas> tarefasOptional = tarefasService.buscarTarefas(id);
        if (!tarefasOptional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não foi encontrada");
        }
        return ResponseEntity.ok().body(tarefasOptional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefasDto tarefaDto) {
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefaDto, tarefas);
        Tarefas tarefasAtual = tarefasService.atualizarTarefas(id, tarefas);
        return ResponseEntity.ok().body(tarefasAtual);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTarefa(@PathVariable Long id) {
        tarefasService.excluirTarefas(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id_usuario}")
    public ResponseEntity<Tarefas> criarTarefaUsuario(@PathVariable Long id_usuario, @RequestBody Tarefas tarefa) {
        Long id_user = tarefa.getId_tarefa();
        if (id_usuario.equals(id_user)) {
            Tarefas novaTarefa = tarefasService.criarTarefaUsuario(tarefa, id_usuario);
            return ResponseEntity.ok().body(novaTarefa);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
