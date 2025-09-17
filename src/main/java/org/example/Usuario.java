package org.example;

public class Usuario {
    private String nomeCompleto;
    private String email;
    private String login;
    private String senha;
    private String cargo;
    private String cpf;

    public Usuario(String nomeCompleto, String email, String login, String senha, String cargo, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
