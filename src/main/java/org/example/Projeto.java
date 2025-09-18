package org.example;

import java.time.LocalDate;

/**
 * Representa um Projeto dentro do sistema.
 * Contém informações como nome, descrição, datas e o gerente responsável.
 */
public class Projeto {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private String status;
    private Usuario gerenteResponsavel;

    /**
     * Construtor para criar um novo Projeto.
     * @param nome Nome do projeto.
     * @param descricao Descrição detalhada do projeto.
     * @param dataInicio Data em que o projeto se inicia.
     * @param dataTerminoPrevista Data prevista para o fim do projeto.
     * @param gerenteResponsavel O objeto Usuario que é o gerente do projeto.
     */
    public Projeto(String nome, String descricao, LocalDate dataInicio, LocalDate dataTerminoPrevista, Usuario gerenteResponsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.gerenteResponsavel = gerenteResponsavel;
        this.status = "Planejado"; // Todo projeto começa como "Planejado"
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public void setDataTerminoPrevista(LocalDate dataTerminoPrevista) {
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(Usuario gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }
}