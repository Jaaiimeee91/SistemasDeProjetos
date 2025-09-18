package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal que gerencia toda a lógica e os dados do sistema.
 * Funciona como o "cérebro" da aplicação, contendo as listas de usuários, projetos e equipes.
 */
public class Sistema {

    private List<Usuario> usuarios;
    private List<Projeto> projetos;
    private List<Equipe> equipes;

    /**
     * Construtor da classe Sistema.
     * Inicializa as listas de dados como listas vazias.
     */
    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.projetos = new ArrayList<>();
        this.equipes = new ArrayList<>();
    }

    /**
     * Cadastra um novo usuário no sistema, adicionando-o à lista de usuários.
     * @param usuario O objeto Usuario a ser adicionado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        System.out.println("Usuário '" + usuario.getNomeCompleto() + "' cadastrado com sucesso!");
    }

    /**
     * Cadastra um novo projeto no sistema.
     * @param projeto O objeto Projeto a ser adicionado.
     */
    public void criarProjeto(Projeto projeto) {
        this.projetos.add(projeto);
        System.out.println("Projeto '" + projeto.getNome() + "' criado com sucesso!");
    }

    /**
     * Cria uma nova equipe no sistema.
     * @param equipe O objeto Equipe a ser adicionado.
     */
    public void criarEquipe(Equipe equipe) {
        this.equipes.add(equipe);
        System.out.println("Equipe '" + equipe.getNome() + "' criada com sucesso!");
    }

    // --- MÉTODOS PARA OBTER AS LISTAS COMPLETAS ---

    /**
     * Retorna a lista de todos os usuários cadastrados.
     * @return uma List de objetos Usuario.
     */
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    /**
     * Retorna a lista de todos os projetos cadastrados.
     * @return uma List de objetos Projeto.
     */
    public List<Projeto> getProjetos() {
        return this.projetos;
    }

    /**
     * Retorna a lista de todas as equipes cadastradas.
     * @return uma List de objetos Equipe.
     */
    public List<Equipe> getEquipes() {
        return this.equipes;
    }
}