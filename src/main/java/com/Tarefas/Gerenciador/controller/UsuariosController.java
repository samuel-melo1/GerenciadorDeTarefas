package com.Tarefas.Gerenciador.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Tarefas.Gerenciador.dto.UsuariosDto;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuariosController {

    private UsuariosService usuariosService;


    UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
  
    }

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("usuariosDto", new UsuariosDto());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuariosDto", new UsuariosDto());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrar(@ModelAttribute("usuariosDto") UsuariosDto usuariosDto, HttpServletRequest request) {
        usuariosDto.setNome(request.getParameter("nome"));
        usuariosDto.setSenha(request.getParameter("senha"));
        usuariosDto.setEmail(request.getParameter("email"));
        var usuarios = new Usuarios();
        BeanUtils.copyProperties(usuariosDto, usuarios);
        Usuarios usuarioSalvo = usuariosService.salvar(usuarios);
        ModelAndView mv = new ModelAndView("redirect:/login");
        mv.addObject("sucesso", "Usuário criado com sucesso");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("usuariosDto") Usuarios usuariosDto, HttpSession session,
            HttpServletRequest request) {
        usuariosDto.setEmail(request.getParameter("email"));
        usuariosDto.setSenha(request.getParameter("senha"));
        Usuarios usuarioSalvo = usuariosService.autenticarUsuario(usuariosDto.getEmail(), usuariosDto.getSenha());
        ModelAndView mv = new ModelAndView("redirect:/tarefas");
        session.setAttribute("id_usuario", usuarioSalvo.getId_usuario());
        return mv;
    }
}
