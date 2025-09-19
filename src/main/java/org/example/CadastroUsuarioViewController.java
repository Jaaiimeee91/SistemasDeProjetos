package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroUsuarioViewController implements Initializable {
    @FXML
    private TextField nomeCompletoTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private PasswordField confirmarSenhaPasswordField;

    @FXML
    private ComboBox<String> cargoComboBox;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button salvarButton;

    /**
     * Ação executada quando o usuário clica no botão "Salvar".
     * Este método irá validar os dados da tela e iniciar o processo de cadastro do novo usuário.
     */
    @FXML
    void onSalvarButtonClick() {
        // 1. Coletar os dados da tela
        String nome = nomeCompletoTextField.getText();
        String cpf = cpfTextField.getText();
        String email = emailTextField.getText();
        String login = loginTextField.getText();
        String senha = senhaPasswordField.getText();
        String confirmarSenha = confirmarSenhaPasswordField.getText();
        String cargo = cargoComboBox.getValue();

        // --- NOVA VALIDAÇÃO (CAMPO POR CAMPO) ---
        if (nome.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'Nome Completo' é obrigatório.");
            return;
        }
        if (cpf.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'CPF' é obrigatório.");
            return;
        }
        if (login.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'Login' é obrigatório.");
            return;
        }
        if (email.isBlank()) { // Vamos adicionar a verificação de e-mail também
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'Email' é obrigatório.");
            return;
        }
        if (senha.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'Senha' é obrigatório.");
            return;
        }
        if (!senha.equals(confirmarSenha)) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", "As senhas não coincidem.");
            return;
        }
        // Poderíamos adicionar mais validações aqui no futuro (ex: CPF válido, etc.)

        // 3. Se todas as validações passaram, criar o objeto Usuario
        Usuario novoUsuario = new Usuario(
                nome,
                cpf,
                login,
                email,
                senha,
                cargo
        );

        // 4. Chamar o método do nosso sistema para cadastrar o usuário
        Main.sistema.cadastrarUsuario(novoUsuario);

        // 5. Mostrar alerta de sucesso
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário '" + nome + "' cadastrado com sucesso!");

        // 6. Limpar os campos da tela após o cadastro
        nomeCompletoTextField.clear();
        cpfTextField.clear();
        emailTextField.clear();
        loginTextField.clear();
        senhaPasswordField.clear();
        confirmarSenhaPasswordField.clear();
        cargoComboBox.getSelectionModel().clearSelection();
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // Não queremos um cabeçalho
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Cria uma lista de cargos
        List<String> cargos = new ArrayList<>();
        cargos.add("Gestor de Projetos");
        cargos.add("Membro da Equipe");
        cargos.add("Administrador");

        // Adiciona a lista de cargos à ComboBox
        cargoComboBox.getItems().addAll(cargos);
    }
}

