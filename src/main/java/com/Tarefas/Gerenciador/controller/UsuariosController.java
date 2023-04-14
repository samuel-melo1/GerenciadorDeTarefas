package com.Tarefas.Gerenciador.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Tarefas.Gerenciador.dto.UsuariosDto;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private UsuariosService usuariosService;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    UsuariosController(UsuariosService usuariosService, AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService) {
        this.usuariosService = usuariosService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        List<Usuarios> usuarios = usuariosService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/id")
    public ResponseEntity<Object> listarIdUsuario(@RequestBody @Valid UsuariosDto usuariosDto) {
        var usuarios = new Usuarios();
        BeanUtils.copyProperties(usuariosDto, usuarios);
        return ResponseEntity.ok().body(usuarios.getId_usuario());
    }

    @PostMapping
    public ResponseEntity<Usuarios> criarUsuario(@RequestBody @Valid UsuariosDto usuariosDto) {
        var usuarios = new Usuarios();
        BeanUtils.copyProperties(usuariosDto, usuarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.salvar(usuarios));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuarios usuarios,HttpServletRequest request,HttpSession session,
            HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(usuarios.getEmail(), usuarios.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuarios.getEmail());
        session.setAttribute("usuario", userDetails);

        return ResponseEntity.ok("User authenticated successfully");
    }

}
