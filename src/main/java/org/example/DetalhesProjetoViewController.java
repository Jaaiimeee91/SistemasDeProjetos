package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetalhesProjetoViewController {

    @FXML
    private Label nomeProjetoLabel;
    @FXML
    private Label gerenteLabel;
    @FXML
    private Label clienteLabel;
    @FXML
    private Label dataInicioLabel;
    @FXML
    private Label dataTerminoLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label descricaoLabel;
    @FXML
    private Button editarButton;
    @FXML
    private Button apagarButton;
    @FXML
    private Button fecharButton;
    private Projeto projetoAtual;

    /**
     * Este método é chamado de fora (pelo Dashboard) para passar
     * o projeto selecionado e preencher os campos da tela.
     * @param projeto O objeto Projeto cujos detalhes serão exibidos.
     */
    public void carregarDadosDoProjeto(Projeto projeto) {
        this.projetoAtual = projeto; // Guarda a referência do projeto
        // Preenche cada Label com os dados do objeto Projeto recebido
        nomeProjetoLabel.setText(projeto.getNome());
        gerenteLabel.setText(projeto.getGerenteResponsavel().getNomeCompleto());
        // Verifica se a informação do cliente existe antes de exibir
        String nomeCliente = projeto.getCliente();
        if (nomeCliente != null && !nomeCliente.isBlank()) {
            clienteLabel.setText(nomeCliente);
        } else {
            clienteLabel.setText("-"); // Se estiver vazio, mostra um traço
        }
        dataInicioLabel.setText(projeto.getDataInicio().toString()); // Converte a data para String
        dataTerminoLabel.setText(projeto.getDataTerminoPrevista().toString());
        statusLabel.setText(projeto.getStatus());
        descricaoLabel.setText(projeto.getDescricao());
    }

    @FXML
    void onFecharButtonClick() {
        // Pega a janela (Stage) a partir de qualquer componente, como o botão
        Stage stage = (Stage) fecharButton.getScene().getWindow();
        // Fecha a janela
        stage.close();
    }

    @FXML
    void onEditarButtonClick() {
        // Lógica "fantasma" que planejamos
        showAlert(Alert.AlertType.INFORMATION, "Funcionalidade Futura", "A opção de editar projetos será implementada na Versão 2.0.");
    }

    @FXML
    void onApagarButtonClick() {
        // Cria um alerta de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Você está prestes a apagar o projeto: " + projetoAtual.getNome());
        alert.setContentText("Você tem certeza de que deseja continuar? Esta ação não pode ser desfeita.");

        // Mostra o alerta e espera a resposta do usuário
        alert.showAndWait().ifPresent(resposta -> {
            // Se a resposta for o botão "OK"...
            if (resposta == ButtonType.OK) {
                // ...chama o método do sistema para apagar o projeto.
                Main.sistema.apagarProjeto(projetoAtual);

                // E fecha a janela de detalhes.
                onFecharButtonClick(); // Reutiliza nosso método de fechar a janela
            }
        });
    }

    // Método ajudante para os alertas (copie de outra classe Controller)
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
