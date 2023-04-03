package com.Tarefas.Gerenciador.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Tarefas.Gerenciador.dto.UsuariosDto;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.service.UsuariosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private UsuariosService usuariosService;

    UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        List<Usuarios> usuarios = usuariosService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuarios> criarUsuario(@RequestBody @Valid UsuariosDto usuariosDto) {
        var usuarios = new Usuarios();
        BeanUtils.copyProperties(usuariosDto, usuarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.salvar(usuarios));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuarios usuarios) {
        boolean authenticated = usuariosService.authenticate(usuarios.getEmail(), usuarios.getSenha());
        if (authenticated) {
            return ResponseEntity.ok("User authenticated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }


}
