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
        Optional<Tarefas> tarefasOptional = tarefasService.criarTarefa(tarefas);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTarefas() {
       Optional<List<Tarefas>> tarefas = tarefasService.listarTarefas();
        return ResponseEntity.ok().body(tarefas.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> buscarTarefa(@PathVariable Long id) {
        Optional<Tarefas> tarefasOptional = tarefasService.buscarTarefas(id);
        return ResponseEntity.ok().body(tarefasOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefasDto tarefaDto) {
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefaDto, tarefas);
        Optional<Tarefas> tarefasAtual = tarefasService.atualizarTarefas(id, tarefas);
        return ResponseEntity.ok().body(tarefasAtual.get());

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
            Optional<Tarefas> novaTarefa = tarefasService.criarTarefaUsuario(tarefa, id_usuario);
            return ResponseEntity.ok().body(novaTarefa.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
