package org.example;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

        // Loop principal do menu
        while (true) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha deixada pelo nextInt()

                switch (opcao) {
                    case 1:
                        cadastrarNovoUsuario(scanner, sistema);
                        break;
                    case 2:
                        listarTodosUsuarios(sistema);
                        break;
                    case 3:
                        criarNovoProjeto(scanner, sistema); // Alterado aqui
                        break;
                    case 4:
                        listarTodosProjetos(sistema); // Adicionado aqui
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema. Até logo!");
                        return; // Encerra o programa
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
                scanner.nextLine(); // Limpa o buffer do scanner para evitar um loop infinito
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Gestão de Projetos ---");
        System.out.println("1. Cadastrar Novo Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Criar Novo Projeto");
        System.out.println("4. Listar Projetos");
        // Adicione outras opções aqui no futuro
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarNovoUsuario(Scanner scanner, Sistema sistema) {
        System.out.println("\n--- Cadastro de Novo Usuário ---");
        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Cargo (Gestor de Projetos, Membro da Equipe, etc): ");
        String cargo = scanner.nextLine();

        // Cria o objeto e cadastra no sistema
        Usuario novoUsuario = new Usuario(nome, cpf, login, email, senha, cargo);
        sistema.cadastrarUsuario(novoUsuario);
    }

    private static void listarTodosUsuarios(Sistema sistema) {
        System.out.println("\n--- Lista de Usuários Cadastrados ---");
        List<Usuario> usuarios = sistema.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado no momento.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.print((i + 1) + ". "); // Mostra "1.", "2.", etc.
                System.out.println(usuarios.get(i).toString());
            }
        }
    }

    private static void criarNovoProjeto(Scanner scanner, Sistema sistema) {
        System.out.println("\n--- Cadastro de Novo Projeto ---");

        if (sistema.getUsuarios().isEmpty()) {
            System.out.println("Erro: É preciso ter pelo menos um usuário cadastrado para ser o gerente.");
            return;
        }

        System.out.print("Nome do Projeto: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        // Tratamento de data com try-catch
        LocalDate dataInicio = null;
        while (dataInicio == null) {
            System.out.print("Data de Início (formato AAAA-MM-DD): ");
            try {
                dataInicio = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }

        LocalDate dataTermino = null;
        while (dataTermino == null) {
            System.out.print("Data de Término Prevista (formato AAAA-MM-DD): ");
            try {
                dataTermino = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }

        // Selecionar o gerente a partir de uma lista
        listarTodosUsuarios(sistema);
        System.out.print("Digite o número correspondente ao usuário que será o gerente: ");
        int indiceGerente = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha

        Usuario gerente = sistema.getUsuarios().get(indiceGerente - 1); // Simplificado, sem validação de índice por enquanto

        // Cria o objeto e cadastra no sistema
        Projeto novoProjeto = new Projeto(nome, descricao, dataInicio, dataTermino, gerente);
        sistema.criarProjeto(novoProjeto);
    }

    private static void listarTodosProjetos(Sistema sistema) {
        System.out.println("\n--- Lista de Projetos Cadastrados ---");
        List<Projeto> projetos = sistema.getProjetos();
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado no momento.");
        } else {
            for (Projeto projeto : projetos) {
                // Futuramente, podemos criar um toString() para Projeto também!
                System.out.println("Nome: " + projeto.getNome() + " | Gerente: " + projeto.getGerenteResponsavel().getNomeCompleto());
            }
        }
    }
}