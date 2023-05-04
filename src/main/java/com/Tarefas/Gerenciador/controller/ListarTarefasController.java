package com.Tarefas.Gerenciador.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.TarefasService;
import com.Tarefas.Gerenciador.service.UsuariosService;

import jakarta.servlet.http.HttpSession;

public class ListarTarefasController {

    private TarefasService tarefasService;
    private UsuariosService usuariosService;

    ListarTarefasController(TarefasService tarefasService, UsuariosService usuariosService){
        this.tarefasService = tarefasService;
        this.usuariosService = usuariosService;
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession session){
        Long id_usuario = (Long) session.getAttribute("id_usuario");
        List<Tarefas> tarefas = tarefasService.listarTarefasUsuarios(id_usuario);
        Usuarios usuario = usuariosService.buscarUsuarios(id_usuario);
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("tarefas", tarefas);
        mv.addObject("usuario", usuario);
        return mv;
    }
    
}
