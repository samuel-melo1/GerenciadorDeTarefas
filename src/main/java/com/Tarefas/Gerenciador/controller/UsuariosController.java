package com.Tarefas.Gerenciador.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuariosController {

    private UsuariosService usuariosService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    UsuariosController(UsuariosService usuariosService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.usuariosService = usuariosService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("usuarios", new Usuarios());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuarios", new Usuarios());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrar(@ModelAttribute("usuarios") Usuarios usuarios, HttpServletRequest request) {
        usuarios.setNome(request.getParameter("nome"));
        String senha = request.getParameter("senha");
        usuarios.setSenha(passwordEncoder.encode(senha));
        usuarios.setEmail(request.getParameter("email"));
        Usuarios usuarioSalvo = usuariosService.salvar(usuarios);
        ModelAndView mv = new ModelAndView("redirect:/login");
        mv.addObject("sucesso", "Usuário criado com sucesso");
        return mv;
    }
/* 
    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("usuarios") Usuarios usuarios, HttpSession session) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarios.getEmail(), usuarios.getSenha()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ModelAndView("redirect:/tarefas");
        } catch (AuthenticationException e) {
            return new ModelAndView("redirect:/error");
        }
    }
    */
}
