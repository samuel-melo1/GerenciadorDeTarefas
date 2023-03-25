package com.Tarefas.Gerenciador.controller;

import java.net.URI;
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
import com.Tarefas.Gerenciador.repository.UsuariosRepository;
import com.Tarefas.Gerenciador.service.TarefasService;
import com.Tarefas.Gerenciador.service.UsuariosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private TarefasService tarefasService;
    private UsuariosService usuariosService;

    TarefasController(TarefasService tarefasService, UsuariosService usuariosService){
        this.tarefasService = tarefasService;
        this.usuariosService = usuariosService;
    }

    @PostMapping
    public ResponseEntity<Object> criarTarefa(@RequestBody @Valid TarefasDto tarefasDto){
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefasDto, tarefas);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.criarTarefa(tarefas));
    }
    
    @GetMapping
    public ResponseEntity<List<Tarefas>> listarTarefas(){
        List<Tarefas> tarefas = tarefasService.listarTarefas();
        return ResponseEntity.ok().body(tarefas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarTarefa(@PathVariable Long id){
        Optional<Tarefas> tarefasOptional = tarefasService.buscarTarefas(id);
        if(!tarefasOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não foi encontrada");
        }
        return ResponseEntity.ok().body(tarefasOptional);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefasDto tarefaDto){
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefaDto, tarefas);
        Tarefas tarefasAtual = tarefasService.atualizarTarefas(id, tarefas);
        return ResponseEntity.ok().body(tarefasAtual);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id){
        tarefasService.excluirTarefas(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/tarefas-usuario")
public ResponseEntity<TarefasDto> criarTarefaUsuario(@RequestBody TarefasDto tarefasDto){
    Optional<Usuarios> usuarioOptional = usuariosService.buscarUsuarios(tarefasDto.getId_usuario());

    if(!usuarioOptional.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado");
    }
    Usuarios usuarios = usuarioOptional.get();
    Tarefas tarefa = new Tarefas(tarefasDto.getTitulo(), tarefasDto.getDescricao(), tarefasDto.getData_inicio(), tarefasDto.getPrazo(), tarefasDto.getStatus(), usuarios);

    Tarefas tarefaCriada = tarefasService.criarTarefa(tarefa);
    return ResponseEntity.created(URI.create("/usuarios/" + id_usuario + "/tarefas/" + tarefaCriadaDto.getId()))
            .body(tarefaCriadaDto);
}



}
