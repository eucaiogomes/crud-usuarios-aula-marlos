package com.senai.crud_usuarios.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.crud_usuarios.dtos.RequisicaoDto;
import com.senai.crud_usuarios.dtos.RespostaDto;
import com.senai.crud_usuarios.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaDto> loginUsuario(@RequestBody RequisicaoDto dados) {
        return ResponseEntity.ok(service.validarLogin(dados));
    }

}
