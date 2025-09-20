package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {

    // Componentes da tela
    @FXML
    private Button gerenciarUsuariosButton;
    @FXML
    private Button gerenciarProjetosButton;
    @FXML
    private TableView<Projeto> projetosTableView;
    @FXML
    private TableColumn<Projeto, String> colunaNome;
    @FXML
    private TableColumn<Projeto, String> colunaGerente;
    @FXML
    private TableColumn<Projeto, String> colunaStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Agora que Projeto tem "Properties", podemos usar a forma simples para todas as colunas!
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colunaGerente.setCellValueFactory(cellData -> {
            Usuario gerente = cellData.getValue().getGerenteResponsavel();
            String nomeGerente = gerente.getNomeCompleto();
            return new SimpleStringProperty(nomeGerente);
        });

        // Carrega os dados do sistema na tabela
        projetosTableView.getItems().setAll(Main.sistema.getProjetos());
    }

    // Métodos dos botões (sem alterações)
    @FXML
    void onGerenciarUsuariosClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/cadastro-usuario-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Usuário");
            stage.setScene(scene);

            // showAndWait() pausa o código aqui até a janela ser fechada
            stage.showAndWait();

            // Depois que a janela fecha, atualizamos a tabela (pode ser útil no futuro)
            atualizarTabelaProjetos();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onGerenciarProjetosClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/cadastro-projeto-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            CadastroProjetoViewController controller = fxmlLoader.getController();
            controller.carregarDados(Main.sistema);

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Projeto");
            stage.setScene(scene);

            // showAndWait() pausa o código aqui até a janela ser fechada
            stage.showAndWait();

            // Depois que a janela fecha, atualizamos a tabela com o novo projeto
            atualizarTabelaProjetos();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void atualizarTabelaProjetos() {
        // Limpa a tabela e a recarrega com a lista mais recente do sistema
        projetosTableView.getItems().clear();
        projetosTableView.getItems().setAll(Main.sistema.getProjetos());
        System.out.println("Tabela de projetos atualizada.");
    }
}