package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // Cria uma instância única do nosso sistema que será usada por toda a aplicação
    public static Sistema sistema = new Sistema();

    @Override
    public void start(Stage stage) throws IOException {
        // CORREÇÃO: Apontamos para a tela de USUÁRIO, que é a nossa tela inicial.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);


        stage.setTitle("Sistema de Gestão de Projetos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}