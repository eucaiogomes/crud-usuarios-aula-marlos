package com.senai.crud_usuarios.dtos;

public class RespostaDto {
    private String mensagem;

    public RespostaDto() {
    }

    public RespostaDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
