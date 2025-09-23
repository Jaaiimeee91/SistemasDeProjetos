package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colunaGerente.setCellValueFactory(cellData -> {
            Usuario gerente = cellData.getValue().getGerenteResponsavel();
            String nomeGerente = gerente.getNomeCompleto();
            return new SimpleStringProperty(nomeGerente);
        });

        projetosTableView.getItems().setAll(Main.sistema.getProjetos());

        projetosTableView.setOnMouseClicked(event -> {
            // Verifica se foi um clique duplo e se a linha clicada não está vazia
            if (event.getClickCount() == 2 && !projetosTableView.getSelectionModel().isEmpty()) {
                // Pega o objeto Projeto que foi selecionado na tabela
                Projeto projetoSelecionado = projetosTableView.getSelectionModel().getSelectedItem();

                // Chama um novo método para abrir a tela de detalhes, passando o projeto selecionado
                abrirTelaDeDetalhes(projetoSelecionado);
            }
        });
    }

    @FXML
    void onGerenciarUsuariosClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/cadastro-usuario-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Usuário");
            stage.setScene(scene);

            stage.showAndWait();
            atualizarTabelaProjetos();

        } catch (IOException e) {
            // Mantemos para nosso controle de desenvolvedor
            e.printStackTrace();
            // E mostramos um alerta amigável para o usuário
            showAlert(Alert.AlertType.ERROR, "Erro ao Abrir Tela", "Não foi possível carregar a tela de cadastro de usuário.");
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

            stage.showAndWait();
            atualizarTabelaProjetos();

        } catch (IOException e) {
            // Mantemos para nosso controle de desenvolvedor
            e.printStackTrace();
            // E mostramos um alerta amigável para o usuário
            showAlert(Alert.AlertType.ERROR, "Erro ao Abrir Tela", "Não foi possível carregar a tela de cadastro de projeto.");
        }
    }

    private void atualizarTabelaProjetos() {
        projetosTableView.getItems().clear();
        projetosTableView.getItems().setAll(Main.sistema.getProjetos());
        System.out.println("Tabela de projetos atualizada.");
    }

    /**
     * Exibe um pop-up de alerta para o usuário.
     * @param alertType O tipo de alerta (ERRO, INFORMAÇÃO, etc.).
     * @param title O título da janela do alerta.
     * @param message A mensagem a ser exibida.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Abre a janela de detalhes para um projeto específico.
     * @param projeto O projeto cujos detalhes serão exibidos.
     */
    /**
     * Abre a janela de detalhes para um projeto específico.
     * @param projeto O projeto cujos detalhes serão exibidos.
     */
    private void abrirTelaDeDetalhes(Projeto projeto) {
        try {
            // Carrega o arquivo FXML da tela de detalhes
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/detalhes-projeto-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Pega uma referência do controller da tela de detalhes
            DetalhesProjetoViewController controller = fxmlLoader.getController();

            // Chama o método no outro controller para passar o objeto Projeto
            controller.carregarDadosDoProjeto(projeto);

            // Cria e exibe a nova janela
            Stage stage = new Stage();
            stage.setTitle("Detalhes do Projeto");
            stage.setScene(scene);

            stage.showAndWait();

            // DEPOIS que a janela de detalhes for fechada, este código abaixo é executado:
            System.out.println("Janela de detalhes fechada. Atualizando a tabela do dashboard...");
            atualizarTabelaProjetos(); // Nosso método que atualiza a tabela

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a tela de detalhes.");
        }
    }
}