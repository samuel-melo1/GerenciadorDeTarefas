package com.Tarefas.Gerenciador.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.Tarefas.Gerenciador.dto.UsuariosDto;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.UsuariosService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UsuariosController {

    private UsuariosService usuariosService;

    UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("usuarios", new Usuarios());
        return mv;
    }
    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuarios", new Usuarios());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrar(@ModelAttribute("usuarios") Usuarios usuarios, HttpServletRequest request ){
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        Usuarios usuario = new Usuarios();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setEmail(email);

        Usuarios usuarioSalvo = usuariosService.salvar(usuarios);
        ModelAndView mv = new ModelAndView("redirect:/login");
        mv.addObject("sucesso", "Usuário criado com sucesso" );
        return mv;

    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("usuarios") Usuarios usuarios, HttpSession session, Model model) {
        Usuarios usuariosSalvar = usuariosService.autenticarUsuario(usuarios.getEmail(), usuarios.getSenha());
        if (usuariosSalvar == null || !usuariosSalvar.getSenha().equals(usuarios.getSenha())) {
            model.addAttribute("erro", "Usuário ou senha inválido");
            return "login";
        }
        session.setAttribute("usuarioLogado", usuariosSalvar);
        return "redirect:/index";
    }
}
