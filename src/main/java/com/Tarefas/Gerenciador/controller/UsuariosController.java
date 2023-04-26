package com.Tarefas.Gerenciador.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.Tarefas.Gerenciador.dto.UsuariosDto;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UsuariosController {

    private UsuariosService usuariosService;

    UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/Paginalogin")
    public String login(Model model, Usuarios usuarios) {
        model.addAttribute("usuarios", new Usuarios());
        return "login";
        
    }

    @PostMapping("/cadastrarUsuarios")
    public String criarUsuario(Model model, @Valid UsuariosDto usuariosDto) {
        var usuarios = new Usuarios();
        BeanUtils.copyProperties(usuariosDto, usuarios);
        model.addAttribute("usuarios", usuarios);
        return "cadastro";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("usuarios") Usuarios usuarios, HttpSession session, Model model) {
        Usuarios usuariosSalvar = usuariosService.buscarPorEmail(usuarios.getEmail());
        if (usuariosSalvar == null || !usuariosSalvar.getSenha().equals(usuarios.getSenha())) {
            model.addAttribute("erro", "Usuário ou senha inválido");
            return "login";
        }
        session.setAttribute("usuarioLogado", usuariosSalvar);
        return "redirect:/index";
    }
}
