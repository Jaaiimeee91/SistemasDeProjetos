package org.example;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;

import java.time.LocalDate;

public class Projeto {
    private final StringProperty nome;
    private final StringProperty descricao;
    private final ObjectProperty<LocalDate> dataInicio;
    private final ObjectProperty<LocalDate> dataTerminoPrevista;
    private final StringProperty status;
    private final ObjectProperty<Usuario> gerenteResponsavel;

    public Projeto(String nome, String descricao, LocalDate dataInicio, LocalDate dataTerminoPrevista, Usuario gerenteResponsavel) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.dataInicio = new SimpleObjectProperty<>(dataInicio);
        this.dataTerminoPrevista = new SimpleObjectProperty<>(dataTerminoPrevista);
        this.status = new SimpleStringProperty("Planejado");
        this.gerenteResponsavel = new SimpleObjectProperty<>(gerenteResponsavel);
    }

    // --- Getters que retornam a propriedade inteira (para o JavaFX) ---
    public StringProperty nomeProperty() { return nome; }
    public StringProperty statusProperty() { return status; }
    public ObjectProperty<Usuario> gerenteResponsavelProperty() { return gerenteResponsavel; }

    // --- Getters normais que retornam o valor (para o resto do nosso código) ---
    public String getNome() { return nome.get(); }
    public String getStatus() { return status.get(); }
    public Usuario getGerenteResponsavel() { return gerenteResponsavel.get(); }

    // (Outros getters e setters podem ser adicionados aqui se necessário)
}