package com.senai.crud_usuarios.controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senai.crud_usuarios.dtos.RequisicaoDto;
import com.senai.crud_usuarios.dtos.RespostaDto;
import com.senai.crud_usuarios.models.UsuarioModel;
import com.senai.crud_usuarios.services.UsuarioService;

@RestController
@RequestMapping("/crud/usuarios") 
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Criar usuário
    @PostMapping("/cadastrar")
    public ResponseEntity<RespostaDto> cadastrarUsuario(@RequestBody RequisicaoDto dados) {
        return ResponseEntity.ok(service.cadastrarUsuarios(dados));
    }

    // Listar todos
    @GetMapping("/listar")
    public ResponseEntity<ArrayList<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    // Buscar por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        UsuarioModel usuario = service.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.badRequest().body("Erro: usuário não encontrado.");
    }

    // Atualizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<RespostaDto> atualizarUsuario(@PathVariable int id, @RequestBody RequisicaoDto dados) {
        return ResponseEntity.ok(service.atualizarUsuario(id, dados));
    }

    // Remover
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<RespostaDto> removerUsuario(@PathVariable int id) {
        return ResponseEntity.ok(service.removerUsuario(id));
    }


@GetMapping("/obter/{id}")
public ResponseEntity<?> obterUsuario(@PathVariable int id) {
    RequisicaoDto usuario = service.obterUsuario(id);
    if (usuario != null) {
        return ResponseEntity.ok(usuario);
    }
    return ResponseEntity.badRequest().body("Erro: usuário não encontrado.");
}


}
