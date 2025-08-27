package com.senai.crud_usuarios.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.senai.crud_usuarios.dtos.RequisicaoDto;
import com.senai.crud_usuarios.dtos.RespostaDto;
import com.senai.crud_usuarios.models.UsuarioModel;

@Service
public class UsuarioService {

    private int ultimoId = 0;
    private ArrayList<UsuarioModel> listaUsuarios = new ArrayList<>();

    // Cadastrar
    public RespostaDto cadastrarUsuarios(RequisicaoDto usuarioDto) {
        RespostaDto respostaDto = new RespostaDto();

        if (usuarioDto.getEmail() == null || usuarioDto.getEmail().isBlank()) {
            respostaDto.setMensagem("Erro: email inválido.");
            return respostaDto;
        }

        // Verifica duplicidade de email
        for (UsuarioModel usuario : listaUsuarios) {
            if (usuario.getEmailUsuario().equalsIgnoreCase(usuarioDto.getEmail())) {
                respostaDto.setMensagem("Erro: email já existe na base.");
                return respostaDto;
            }
        }

        ultimoId++;
        UsuarioModel usuario = new UsuarioModel();
        usuario.setIdUsuario(ultimoId);
        usuario.setNomeUsuario(usuarioDto.getNome());
        usuario.setEmailUsuario(usuarioDto.getEmail());
        usuario.setSenhaUsuario(usuarioDto.getSenha());

        listaUsuarios.add(usuario);

        respostaDto.setMensagem("Usuário cadastrado com sucesso!");
        return respostaDto;
    }

    // Listar todos
    public ArrayList<UsuarioModel> listarUsuarios() {
        return listaUsuarios;
    }

    // Buscar por ID
    public UsuarioModel buscarPorId(int id) {
        for (UsuarioModel usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == id) {
                return usuario;
            }
        }
        return null;
    }

    // Atualizar
    public RespostaDto atualizarUsuario(int id, RequisicaoDto dados) {
        RespostaDto resposta = new RespostaDto();

        if (dados.getEmail() == null || dados.getEmail().isBlank()) {
            resposta.setMensagem("Erro: email inválido.");
            return resposta;
        }

        for (UsuarioModel usuario : listaUsuarios) {
            // Se o email já existe em outro usuário, bloqueia
            if (usuario.getEmailUsuario().equalsIgnoreCase(dados.getEmail())
                    && usuario.getIdUsuario() != id) {
                resposta.setMensagem("Erro: já existe um usuário com esse email.");
                return resposta;
            }
        }

        // Atualizando usuário
        for (UsuarioModel usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == id) {
                usuario.setNomeUsuario(dados.getNome());
                usuario.setEmailUsuario(dados.getEmail());
                usuario.setSenhaUsuario(dados.getSenha());
                resposta.setMensagem("Usuário atualizado com sucesso!");
                return resposta;
            }
        }

        resposta.setMensagem("Erro: usuário não encontrado.");
        return resposta;
    }

    // Remover
    public RespostaDto removerUsuario(int id) {
        RespostaDto resposta = new RespostaDto();

        UsuarioModel usuarioEncontrado = null;
        for (UsuarioModel usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == id) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado != null) {
            listaUsuarios.remove(usuarioEncontrado);
            resposta.setMensagem("Usuário removido com sucesso!");
        } else {
            resposta.setMensagem("Erro: usuário não encontrado.");
        }

        return resposta;
    }

    public RequisicaoDto obterUsuario(int id) {
        for (UsuarioModel u : listaUsuarios) {
            if (u.getIdUsuario() == id) {
                RequisicaoDto usuarioDto = new RequisicaoDto();
                usuarioDto.setIdUsuario(u.getIdUsuario());
                usuarioDto.setNome(u.getNomeUsuario());
                usuarioDto.setEmail(u.getEmailUsuario());
                usuarioDto.setSenha(u.getSenhaUsuario());
                return usuarioDto;
            }
        }
        // Se não encontrar, retorna null
        return null;
    }

    public RespostaDto validarLogin(RequisicaoDto dados) {
        RespostaDto respostaDto = new RespostaDto();
        for (UsuarioModel u : listaUsuarios) {

            if (dados.getEmail().equals(u.getEmailUsuario()) && dados.getSenha().equals(u.getSenhaUsuario())) {
                respostaDto.setMensagem("Login Bem sucedido");
                return respostaDto;
            }
        }
        respostaDto.setMensagem("Erro: login invalido");
        return respostaDto;
    }
}
