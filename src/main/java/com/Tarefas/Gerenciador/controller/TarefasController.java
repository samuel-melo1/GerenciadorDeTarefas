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


    TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
        
    }

    @PostMapping
    public ResponseEntity<Tarefas> criarTarefa(@RequestBody @Valid TarefasDto tarefasDto) {
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefasDto, tarefas);
        tarefasService.criarTarefa(tarefas);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefas);
    }

    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTarefas() {
        return ResponseEntity.ok().body(tarefasService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> buscarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok().body(tarefasService.buscarTarefa(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefasDto tarefaDto) {
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefaDto, tarefas);
        return ResponseEntity.ok().body(tarefasService.atualizarTarefas(id, tarefas));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefas> deletarTarefa(@PathVariable Long id) {
        tarefasService.excluirTarefas(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id_usuario}")
    public ResponseEntity<Tarefas> criarTarefaUsuario(@PathVariable Long id_usuario, @RequestBody Tarefas tarefa) {
        Long id_user = tarefa.getId_tarefa();
        if (id_usuario.equals(id_user)) {
            return ResponseEntity.ok().body(tarefasService.criarTarefaUsuario(tarefa, id_usuario));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
