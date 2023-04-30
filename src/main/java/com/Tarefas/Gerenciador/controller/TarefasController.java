package com.Tarefas.Gerenciador.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import com.Tarefas.Gerenciador.dto.TarefasDto;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.service.TarefasService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TarefasController {

    private TarefasService tarefasService;

    TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;

    }
    @GetMapping("/tarefas")
    public ModelAndView tarefasView(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tarefas", new Tarefas());
        return mv;
    }

    @PostMapping("/tarefas")
    public String criarTarefa(@ModelAttribute("tarefasDto") @Valid TarefasDto tarefasDto, HttpSession session,
            Model model) {
        var tarefas = new Tarefas();
        BeanUtils.copyProperties(tarefasDto, tarefas);
        tarefasService.criarTarefa(tarefas);
        List<Tarefas> listaTarefas = tarefasService.listarTarefas();
        model.addAttribute("listaTarefas", listaTarefas);
        return "index";
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
