package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CadastroProjetoViewController {

    @FXML
    private TextField nomeProjetoTextField;

    @FXML
    private TextField clienteTextField;

    @FXML
    private ComboBox<Usuario> gerenteComboBox;

    @FXML
    private DatePicker dataInicioDatePicker;

    @FXML
    private DatePicker dataTerminoDatePicker;

    @FXML
    private TextArea descricaoTextArea;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button criarProjetoButton;

    private Sistema sistema; // Variável para guardar a referência do nosso "cérebro"

    /**
     * Este método será chamado pelo Main para "injetar" a instância do Sistema.
     * @param sistema A instância principal do sistema com todos os dados.
     */
    public void carregarDados(Sistema sistema) {
        this.sistema = sistema;
        gerenteComboBox.getItems().addAll(this.sistema.getUsuarios());

        // 2. Define como cada Usuario deve ser exibido na lista (mostrando apenas o nome)
        gerenteComboBox.setConverter(new StringConverter<Usuario>() {
            @Override
            public String toString(Usuario usuario) {
                // Se o objeto não for nulo, retorna o nome. Senão, retorna null.
                return usuario == null ? null : usuario.getNomeCompleto();
            }

            @Override
            public Usuario fromString(String string) {
                // Não precisamos disso para uma ComboBox não-editável, então retorna null.
                return null;
            }
        });
    }

    @FXML
    void onCriarProjetoClick() {
        // 1. Coletar os dados da tela
        String nome = nomeProjetoTextField.getText();
        String cliente = clienteTextField.getText(); // Opcional, não usaremos no construtor por enquanto
        LocalDate dataInicio = dataInicioDatePicker.getValue();
        LocalDate dataTermino = dataTerminoDatePicker.getValue();
        String descricao = descricaoTextArea.getText();
        Usuario gerente = gerenteComboBox.getValue(); // Pega o OBJETO Usuario inteiro que foi selecionado

        // 2. Validar os dados
        if (nome.isBlank() || dataInicio == null || dataTermino == null || gerente == null) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "Os campos Nome do Projeto, Data de Início, Data de Término e Gerente são obrigatórios.");
            return; // Para a execução se a validação falhar
        }

        // 3. Criar o objeto Projeto
        Projeto novoProjeto = new Projeto(nome, descricao, dataInicio, dataTermino, gerente);

        // 4. Chamar o método do nosso sistema para cadastrar o projeto
        Main.sistema.criarProjeto(novoProjeto);

        // 5. Mostrar alerta de sucesso e limpar a tela
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Projeto '" + nome + "' criado com sucesso!");
        limparCampos();
    }

    // Método para limpar os campos do formulário
    private void limparCampos() {
        nomeProjetoTextField.clear();
        clienteTextField.clear();
        dataInicioDatePicker.setValue(null);
        dataTerminoDatePicker.setValue(null);
        descricaoTextArea.clear();
        gerenteComboBox.getSelectionModel().clearSelection();
    }

    // Método para exibir pop-ups de alerta
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
