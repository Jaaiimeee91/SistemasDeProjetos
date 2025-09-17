package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroUsuarioViewController {
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

        // --- INÍCIO DA VALIDAÇÃO ---

        // 2. Verificar se campos essenciais estão vazios
        if (nome.isBlank() || login.isBlank() || email.isBlank() || senha.isBlank()) {
            System.out.println("ERRO DE VALIDAÇÃO: Por favor, preencha todos os campos obrigatórios.");
            // Futuramente, podemos mostrar um pop-up de erro para o usuário aqui.
            return; // Para a execução do método se houver um erro.
        }

        // 3. Verificar se as senhas são iguais
        if (!senha.equals(confirmarSenha)) {
            System.out.println("ERRO DE VALIDAÇÃO: As senhas não coincidem.");
            // Futuramente, podemos mostrar outro pop-up de erro.
            return; // Para a execução do método se houver um erro.
        }

        // --- FIM DA VALIDAÇÃO ---

        // 3. Se todas as validações passaram, criar o objeto Usuario
        Usuario novoUsuario = new Usuario(
                nome,
                login,
                email,
                senha, // Usamos a senha já validada
                cargo,
                cpf
        );

        // 4. Testar se o objeto foi criado corretamente
        System.out.println("Objeto Usuário criado com sucesso na memória!");
        System.out.println("Nome recuperado do objeto: " + novoUsuario.getNomeCompleto());

        // TODO: O último passo será salvar o objeto 'novoUsuario' no banco de dados.
    }


}

