package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Equipe de trabalho no sistema.
 * Uma equipe é composta por um nome, uma descrição e uma lista de membros (Usuários).
 */
public class Equipe {
    private String nome;
    private String descricao;
    private List<Usuario> membros;

    /**
     * Construtor para criar uma nova Equipe.
     * @param nome O nome da equipe.
     * @param descricao Uma breve descrição sobre o propósito da equipe.
     */
    public Equipe(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>(); // A equipe começa com uma lista de membros vazia.
    }

    /**
     * Adiciona um novo membro à lista da equipe.
     * @param membro O objeto Usuario a ser adicionado.
     */
    public void adicionarMembro(Usuario membro) {
        this.membros.add(membro);
    }

    /**
     * Remove um membro da lista da equipe.
     * @param membro O objeto Usuario a ser removido.
     */
    public void removerMembro(Usuario membro) {
        this.membros.remove(membro);
    }

    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getMembros() {
        return membros;
    }
}