package com.Tarefas.Gerenciador.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.Tarefas.Gerenciador.dto.TarefasDto;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.TarefasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TarefasController {

    private TarefasService tarefasService;


    TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;

    }

    @GetMapping("/tarefas")
    public ModelAndView tarefasView() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tarefas", new Tarefas());
        return mv;
    }

    @PostMapping("/tarefas")
    public ModelAndView criarTarefa(@ModelAttribute("tarefasDto") @Valid TarefasDto tarefasDto, 
            @RequestParam("data_inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
            @RequestParam("prazo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate prazo,
            HttpSession session, HttpServletRequest request) {
        tarefasDto.setData_inicio(dataInicio);
        tarefasDto.setPrazo(prazo);
        tarefasDto.setTitulo(request.getParameter("titulo"));
        tarefasDto.setDescricao(request.getParameter("descricao"));
        tarefasDto.setPrioridade(request.getParameter("prioridade"));
        tarefasDto.setStatus(request.getParameter("status"));
        var tarefas = new Tarefas();
        Usuarios usuario = new Usuarios();
        BeanUtils.copyProperties(tarefasDto, tarefas);
        Long id_usuario = (Long) session.getAttribute("id_usuario");
        usuario.setId_usuario(id_usuario);
        tarefas.setUsuarios(usuario);
        
        ModelAndView mv = new ModelAndView("index");
        tarefasService.criarTarefa(tarefas);
        return mv;
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
}
