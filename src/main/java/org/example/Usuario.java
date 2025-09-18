package org.example;

public class Usuario extends Pessoa { // Adicionamos "extends Pessoa"

    private String login;
    private String email;
    private String senha;
    private String cargo;
    // Os campos nomeCompleto e cpf foram REMOVIDOS daqui, pois já existem em Pessoa.

    /**
     * Construtor para criar um objeto Usuario.
     */
    public Usuario(String nomeCompleto, String cpf, String login, String email, String senha, String cargo) {
        // Chamamos o construtor da classe "pai" (Pessoa) para guardar o nome e o cpf.
        super(nomeCompleto, cpf);

        // Guardamos os atributos que são específicos do Usuario.
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    // --- Getters e Setters para os atributos de Usuario ---

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Sobrescreve o método toString() para fornecer uma representação textual do objeto Usuario.
     * Isso é um exemplo de Polimorfismo (override).
     * @return Uma String formatada com os dados do usuário.
     */
    @Override
    public String toString() {
        // Note que getNomeCompleto() e getCpf() são herdados da classe Pessoa!
        return "Usuário {" +
                "Nome='" + getNomeCompleto() + '\'' +
                ", CPF='" + getCpf() + '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Email='" + getEmail() + '\'' +
                ", Cargo='" + getCargo() + '\'' +
                '}';
    }
}