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

        criarDadosIniciais();
    }

    private void criarDadosIniciais() {
        // Cria e cadastra um Gerente
        Usuario gerentePadrao = new Usuario("Jaime Silva (Gerente)", "111.111.111-11", "jaime.gerente", "jaime@email.com", "123", "Gestor de Projetos");
        this.usuarios.add(gerentePadrao);

        // Cria e cadastra um Membro da Equipe
        Usuario devPadrao = new Usuario("Bruna Lima (Dev)", "222.222.222-22", "bruna.dev", "bruna@email.com", "123", "Membro da Equipe");
        this.usuarios.add(devPadrao);


        System.out.println(">>> Dados de teste carregados: 2 usuários criados automaticamente.");

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