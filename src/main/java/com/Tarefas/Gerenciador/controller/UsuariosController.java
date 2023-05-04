package com.Tarefas.Gerenciador.controller;
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

    UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
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
        usuarios.setSenha(request.getParameter("senha"));
        usuarios.setEmail(request.getParameter("email"));
        Usuarios usuarioSalvo = usuariosService.salvar(usuarios);
        ModelAndView mv = new ModelAndView("redirect:/login");
        mv.addObject("sucesso", "Usuário criado com sucesso");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute("usuarios") Usuarios usuarios, HttpSession session,
            HttpServletRequest request) {
        usuarios.setEmail(request.getParameter("email"));  
        usuarios.setSenha( request.getParameter("senha")); 
        Usuarios usuarioSalvo = usuariosService.autenticarUsuario(usuarios.getEmail(), usuarios.getSenha());
        ModelAndView mv = new ModelAndView("redirect:/tarefas");
        mv.addObject("Logado", "Usuário logado ");
        session.setAttribute("id_usuario", usuarioSalvo.getId_usuario());
        System.out.println(session.getAttribute("id_usuario"));
        return mv;
    }
}
