package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Esta linha carrega o nosso arquivo de design FXML
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/cadastro-usuario-view.fxml"));

        // Cria a "cena" com o conteúdo do FXML e define um tamanho inicial
        Scene scene = new Scene(fxmlLoader.load(), 600, 500); // (Largura, Altura)

        // Define o título da janela
        stage.setTitle("Sistema de Gestão de Projetos");

        // Coloca a cena na "janela" principal (o Stage)
        stage.setScene(scene);

        // Exibe a janela para o usuário
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}