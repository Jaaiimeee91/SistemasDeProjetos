package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardViewController {

    @FXML
    private Button gerenciarUsuariosButton;

    @FXML
    private Button gerenciarProjetosButton;

    @FXML
    void onGerenciarUsuariosClick() {
        try {
            // Carrega o arquivo FXML da tela que queremos abrir
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/cadastro-usuario-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Cria um novo "Palco" (Stage), que é uma nova janela
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Usuário");
            stage.setScene(scene);

            // Exibe a nova janela
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Aqui poderíamos usar nosso showAlert de erro
        }
    }

    @FXML
    void onGerenciarProjetosClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/cadastro-projeto-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Pega o controller da tela que acabamos de carregar
            CadastroProjetoViewController controller = fxmlLoader.getController();
            // E chama o método para carregar os dados (a lista de gerentes)
            controller.carregarDados(Main.sistema);

            // Cria o novo palco (janela)
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Projeto");
            stage.setScene(scene);

            // Exibe a nova janela
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
