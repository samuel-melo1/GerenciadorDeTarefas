package com.Tarefas.Gerenciador.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Tarefas.Gerenciador.dto.TarefasDto;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.repository.TarefasRepository;
import com.Tarefas.Gerenciador.repository.UsuariosRepository;

@Service
public class TarefasService {

    private TarefasRepository tarefasRepository;
    private UsuariosRepository usuariosRepository;
 
    TarefasService(TarefasRepository tarefasRepository, UsuariosRepository usuariosRepository ){
        this.tarefasRepository = tarefasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public Object criarTarefa(Tarefas tarefas){
        
        if(tarefasRepository.existsByTitulo(tarefas.getTitulo())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Titulo já em uso");
        }
        if(tarefasRepository.existsByDescricao(tarefas.getDescricao())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Descrição já em uso");
        }

        return tarefasRepository.save(tarefas);
        
    }
    public List<Tarefas> listarTarefas(){
        return tarefasRepository.findAll();

    }
    public Optional<Tarefas> buscarTarefas(Long id){
         return tarefasRepository.findById(id);
      
    }
    public Tarefas atualizarTarefas(Long id, Tarefas tarefasAtualizada){
        Optional<Tarefas> tarefasAntiga = tarefasRepository.findById(id);

        if(!tarefasAntiga.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        Tarefas tarefas = tarefasAntiga.get();
        tarefas.setTitulo(tarefasAtualizada.getTitulo());
        tarefas.setDescricao(tarefasAtualizada.getDescricao());
        tarefas.setData_inicio(tarefasAtualizada.getData_inicio());
        tarefas.setPrazo(tarefasAtualizada.getPrazo());
        tarefas.setStatus(tarefasAtualizada.getStatus());
        return tarefasRepository.save(tarefas);
    }
    public void excluirTarefas(Long id){
        Optional<Tarefas> tarefas = tarefasRepository.findById(id);

        if(!tarefas.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        tarefasRepository.delete(tarefas.get());
    }

    public Tarefas criarTarefaUsuario(TarefasDto tarefasDto, Long id_usuario){

        Optional<Usuarios> usuarioOptional = usuariosRepository.findById(id_usuario);

        if(!usuarioOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        Usuarios usuario = usuarioOptional.get();
        Tarefas tarefa = new Tarefas();
        tarefa.setTitulo(tarefasDto.getTitulo());
        tarefa.setDescricao(tarefasDto.getDescricao());
        tarefa.setData_inicio(tarefasDto.getData_inicio());
        tarefa.setPrazo(tarefasDto.getPrazo());
        tarefa.setStatus(tarefasDto.getStatus());
        tarefa.setUsuario(usuario);

        return tarefasRepository.save(tarefa);
    
    }
}