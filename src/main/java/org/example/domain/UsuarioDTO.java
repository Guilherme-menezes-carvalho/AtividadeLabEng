package org.example.domain;

public class UsuarioDTO {

    private String nome;
    private String senha;


    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(this.nome);
        usuarioEntity.setSenha(this.senha);
        return usuarioEntity;
    }
}
