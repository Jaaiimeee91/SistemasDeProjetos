package org.example;

public class Pessoa {
    private String nomeCompleto;
    private String cpf;

    /**
     * Construtor para criar um objeto Pessoa.
     * @param nomeCompleto O nome completo da pessoa.
     * @param cpf O CPF da pessoa.
     */
    public Pessoa(String nomeCompleto, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    // --- Getters e Setters ---

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}