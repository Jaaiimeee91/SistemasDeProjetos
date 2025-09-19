import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class DatabaseManager {

 // Configurações de conexão (sem nome do banco inicialmente)
 private static final String URL = "jdbc:mysql://localhost:3306/";
 private static final String USER = "henry";

 private static final String PASSWORD = "";
 private static final String DATABASE_NAME = "ProjetoTarefas";
 private Connection connection;
// public DatabaseManager() {
//  // Apenas o Construtor - não conecta automaticamente
// }

 // Conecta ao banco de dados, e cria se não existir
 public boolean conectar() {
  try {
   // Primeiro, tentar conectar ao banco específico
   connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
   System.out.println("Conectado ao banco de dados existente: " + DATABASE_NAME);
   return true;

  } catch (SQLException e) {
   // Se o banco não existir, erro 1049 (Unknown database)
   if (e.getErrorCode() == 1049) {
    System.out.println("Banco não encontrado. Criando banco de dados...");
    return criarBancoETabelas();
   } else {
    System.err.println("Erro ao conectar: " + e.getMessage());
    return false;
   }
  }
 }

 //Cria o banco de dados e as tabelas necessárias
 private boolean criarBancoETabelas() {
  Connection tempConexao = null;
  try {
   // Conectar ao MySQL sem especificar banco
   tempConexao = DriverManager.getConnection(URL, USER, PASSWORD);
   Statement stmt = tempConexao.createStatement();

   // Criar banco de dados
   stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
   System.out.println("Banco de dados criado: " + DATABASE_NAME);

   // Usar o banco criado
   stmt.executeUpdate("USE " + DATABASE_NAME);

   // Criar tabela de usuários
   String createTableSQLUsuarios = """
   CREATE TABLE IF NOT EXISTS `Usuarios`(
    `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
    `NomeCompleto` varchar(100) NOT NULL,
    `Cpf` varchar(100) NOT NULL,
    `Email` varchar(100) NOT NULL,
    `Cargo` varchar(100) NOT NULL,
    `Login` varchar(100) NOT NULL,
    `Senha` varchar(100) NOT NULL,
    `Perfil` varchar(100) NOT NULL,
    PRIMARY KEY(`idUsuario`)
   ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
   """;

   // Criar tabela de equipes
   String createTableSQLEquipe = """
   CREATE TABLE IF NOT EXISTS `Equipes`(
    `idEquipe` int(11) NOT NULL AUTO_INCREMENT,
    `NomeEquipe` varchar(100) NOT NULL,
    `DescricaoEquipe` varchar(255) NOT NULL,
    PRIMARY KEY(`idEquipe`),
    UNIQUE KEY `Equipes_UNIQUE`(`NomeEquipe`)
   ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
   """;

   // Criar tabela de membros das equipes
   String createTableSQLMembrosEquipe = """
   CREATE TABLE IF NOT EXISTS `MembrosEquipes`(
   `idEquipe` int(11) NOT NULL,
    `IdUsuario` int(11) NOT NULL,
     UNIQUE KEY unique_colunas (idEquipe, IdUsuario)
   ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
   """;

   // Criar tabela de membros de projetos
   String createTableSQLProjetos = """
   CREATE TABLE IF NOT EXISTS `Projetos`(
    `idProjeto` int(11) NOT NULL AUTO_INCREMENT,
    `NomeProjeto` varchar(100) NOT NULL,
    `DescricaoProjeto` varchar(255) NOT NULL,
    `DataInicio` datetime NOT NULL,
    `DataTermino` datetime NOT NULL,
    `Status` varchar(100) NOT NULL,
    `IdGerente` int(11) NOT NULL,
    `IdEquipe` int(11) NOT NULL,
    PRIMARY KEY(`idProjeto`),
    UNIQUE KEY `Projetos_UNIQUE`(`NomeProjeto`)
   ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
   """;

   String createTableSQLTarefasProjetos = """
   CREATE TABLE IF NOT EXISTS `TarefasProjetos`(
    `idProjeto` int(11) NOT NULL,
    `idUsuario` int(11) NOT NULL,
    `idEquipe` int(11) NOT NULL,
    `NomeTarefa` varchar(100) NOT NULL,
    `DescricaoTarefa` varchar(255) NOT NULL,
    `DataInicioTarefa` datetime NOT NULL,
    `DataFinalTarefa` datetime NOT NULL,
    `StatusTarefa` varchar(100) NOT NULL
   ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
   """;

   stmt.executeUpdate(createTableSQLUsuarios);
   System.out.println("Tabela 'Usuarios' criada");

   stmt.executeUpdate(createTableSQLEquipe);
   System.out.println("Tabela 'Equipes' criada");

   stmt.executeUpdate(createTableSQLMembrosEquipe);
   System.out.println("Tabela 'MembrosEquipes' criada");

   stmt.executeUpdate(createTableSQLProjetos);
   System.out.println("Tabela 'Projetos' criada");

   stmt.executeUpdate(createTableSQLTarefasProjetos);
   System.out.println("Tabela 'TarefasProjetos' criada");

   stmt.close();

   // Agora conectar ao banco recém-criado
   connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
   System.out.println("Conectado ao banco recém-criado");

   return true;

  } catch (SQLException e) {
   System.err.println("Erro ao criar banco/tabelas: " + e.getMessage());
   return false;
  } finally {
   // Fechar conexão temporária
   if (tempConexao != null) {
    try {
     tempConexao.close();
    } catch (SQLException e) {
     System.err.println("Erro ao fechar conexão temporária: " + e.getMessage());
    }
   }
  }
 }//Final de criarBancoETabelas

 // Verifica se o banco de dados existe
 public boolean bancoExiste() {
  try (Connection tempConexao = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = tempConexao.createStatement(); ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '" + DATABASE_NAME + "'")) {

   return rs.next(); // Retorna true se encontrou o banco

  } catch (SQLException e) {
   System.err.println("Erro ao verificar existência do banco: " + e.getMessage());
   return false;
  }
 }

 //Verifica se a tabela de usuários existe
 public boolean tabelaUsuariosExiste() {
  if (connection == null) {
   return false;
  }

  try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'Usuarios'")) {

   return rs.next(); // Retorna true se encontrou a tabela

  } catch (SQLException e) {
   System.err.println("Erro ao verificar existência da tabela: " + e.getMessage());
   return false;
  }
 }

 //desconectar do banco, ao sair.
 public void desconectar() {
  try {
   if (connection != null && !connection.isClosed()) {
    connection.close();
    System.out.println("Conexão com o banco fechada.");
   }
  } catch (SQLException e) {
   System.err.println("Erro ao desconectar: " + e.getMessage());
  }
 }

 
  public boolean AutenticaUsuario(String Login, String Senha) 
  {
   if (connection == null) {
    System.err.println("Não conectado ao banco!");
    return false;
   }
 String AutenticaUsuarioSQL = """
   SELECT Login,Senha from Usuarios where Login = ? and Senha = ? 
   """;
  try (PreparedStatement stmt = connection.prepareStatement(AutenticaUsuarioSQL)) {
    stmt.setString(1, Login);
    stmt.setString(2, Senha);

    int linhasExecutadas = stmt.executeUpdate();
    return linhasExecutadas > 0;

   } catch (SQLException e) {
    System.err.println("Erro ao consultar: " + e.getMessage());
    return false;
   }
  } //Final de AutenticaUsuario
  
  
  /////////////////////////////
  public boolean CriaUsuario(String Cpf, String NomeCompleto, String Email, String Cargo, String Login, String Senha, String Perfil)   {
   if (connection == null) {
    System.err.println("Não conectado ao banco!");
    return false;
   }

   String CriaUsuarioSQL = """
   INSERT INTO Usuarios(Cpf, NomeCompleto, Email, Cargo, Login, Senha, Perfil) VALUES( ? , ? , ? , ? , ? , ? , ? )
   """;
   try (PreparedStatement stmt = connection.prepareStatement(CriaUsuarioSQL)) {
    stmt.setString(1, Cpf);
    stmt.setString(2, NomeCompleto);
    stmt.setString(3, Email);
    stmt.setString(4, Cargo);
    stmt.setString(5, Login);
    stmt.setString(6, Senha);
    stmt.setString(7, Perfil);

    int linhasExecutadas = stmt.executeUpdate();
    return linhasExecutadas > 0;

   } catch (SQLException e) {
    System.err.println("Erro ao inserir: " + e.getMessage());
    return false;
   }
  } //Final de CriaUsuario
  
  
  
 public boolean AlteraUsuario(int idUsuario, String Cpf, String NomeCompleto, String Email, String Cargo, String Login, String Senha, String Perfil)
 {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String AlteraUsuarioSQL = """
  UPDATE Usuarios set Cpf = ? , NomeCompleto = ? , Email = ? , Cargo = ? , Login = ? , Senha = ? , Perfil = ? where idUsuario = ?
   """;
  try (PreparedStatement stmt = connection.prepareStatement(AlteraUsuarioSQL)) {
   stmt.setInt(1, idUsuario);
   stmt.setString(2, Cpf);
   stmt.setString(3, NomeCompleto);
   stmt.setString(4, Email);
   stmt.setString(5, Cargo);
   stmt.setString(6, Login);
   stmt.setString(7, Senha);
   stmt.setString(8, Perfil);

   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Atualizar: " + e.getMessage());
   return false;
  }

} // final de AlteraUsuario

 public boolean DeletaUsuario(int idUsuario)
 {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String DeletaUsuarioSQL = """  
DELETE usu, meq, tpr FROM `Usuarios` usu LEFT JOIN `MembrosEquipes` meq ON meq.IdUsuario = usu.idUsuario LEFT JOIN `TarefasProjetos` tpr ON tpr.idUsuario = usu.idUsuario WHERE usu.idUsuario = ?
""";
   
  try (PreparedStatement stmt = connection.prepareStatement(DeletaUsuarioSQL)) {
   stmt.setInt(1, idUsuario);
   
   int linhasExecutadasDeletar = stmt.executeUpdate();
   return linhasExecutadasDeletar > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Deletar: " + e.getMessage());
   return false;
  }

} // final de DeletaUsuario


 public boolean CriaEquipe (String NomeEquipe, String DescricaoEquipe)
 {
    if (connection == null) {
    System.err.println("Não conectado ao banco!");
    return false;
   }

   String CriaEquipeSQL = """
   INSERT INTO Equipes(NomeEquipe,DescricaoEquipe) VALUES(? , ? )
   """;
   try (PreparedStatement stmt = connection.prepareStatement(CriaEquipeSQL)) {
    stmt.setString(1, NomeEquipe);
    stmt.setString(2, DescricaoEquipe);
    
    int linhasExecutadas = stmt.executeUpdate();
    return linhasExecutadas > 0;

   } catch (SQLException e) {
    System.err.println("Erro ao inserir: " + e.getMessage());
    return false;
   }
  } //Final de CriaEquipe


 public boolean EditaEquipe(int idEquipe, String NomeEquipe, String DescricaoEquipe)
  {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String AlteraEquipeSQL = """
  UPDATE Equipes set NomeEquipe = ? , DescricaoEquipe = ?  WHERE idEquipe = ?
   """;
  try (PreparedStatement stmt = connection.prepareStatement(AlteraEquipeSQL)) {
   stmt.setInt(1, idEquipe);
   stmt.setString(2, NomeEquipe);
   stmt.setString(3, DescricaoEquipe);
   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Atualizar: " + e.getMessage());
   return false;
  }
 
} // final de EditaEquipe

 public boolean AtribuiUsuarioEquipe(int idEquipe,int idUsuario)
  {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String AtribuiUsuarioEquipeSQL = """
  INSERT INTO MembrosEquipes(idEquipe,idUsuario) VALUES(? , ? )
   """;
  try (PreparedStatement stmt = connection.prepareStatement(AtribuiUsuarioEquipeSQL)) {
   stmt.setInt(1, idEquipe);
   stmt.setInt(2, idUsuario);
   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Atribuir: " + e.getMessage());
   return false;
  }
 
} // final de AtribuiUsuarioEquipe

 public boolean DeletaUsuarioEquipe(int idEquipe,int idUsuario)
  {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String DeletaUsuarioEquipeSQL = """
  DELETE FROM MembrosEquipes where idEquipe = ? and idUsuario = ?
   """;
  try (PreparedStatement stmt = connection.prepareStatement(DeletaUsuarioEquipeSQL)) {
   stmt.setInt(1, idEquipe);
   stmt.setInt(2, idUsuario);
   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Deletar: " + e.getMessage());
   return false;
  }
 
} // final de DeletaUsuarioEquipe

 public boolean CriaProjeto(String NomeProjeto, String DescricaoProjeto, LocalDateTime DataInicio, LocalDateTime  DataTermino, String Status, int IdGerente, int IdEquipe )
  {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String CriaProjetoSQL = """
  INSERT INTO Projetos(NomeProjeto,DescricaoProjeto,DataInicio, DataTermino,Status, IdGerente, IdEquipe) VALUES(? , ? , ? , ? , ? , ? , ?)
   """;
  try (PreparedStatement stmt = connection.prepareStatement(CriaProjetoSQL)) {
   stmt.setString(1, NomeProjeto);
   stmt.setString(2, DescricaoProjeto);
   stmt.setObject(3, DataInicio);
   stmt.setObject(4, DataTermino);
   stmt.setString(5, Status);
   stmt.setInt(6, IdGerente);
   stmt.setInt(7, IdEquipe);
   
   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Criar: " + e.getMessage());
   return false;
  }
 
} // final de CriaProjeto

 public boolean EditaProjeto(int idProjeto, String NomeProjeto, String DescricaoProjeto, LocalDateTime DataInicio, LocalDateTime  DataTermino, String Status, int IdGerente, int IdEquipe )
   {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String AlteraProjetoSQL = """
  UPDATE Projetos set idProjeto = ? NomeProjeto = ? DescricaoProjeto = ? DataInicio = ? DataTermino = ? Status = ? IdGerente = ? IdEquipe = ? WHERE idProjeto = ?
   """;
  try (PreparedStatement stmt = connection.prepareStatement(AlteraProjetoSQL)) {
   stmt.setInt(1, idProjeto);
   stmt.setString(2, NomeProjeto);
   stmt.setString(3, DescricaoProjeto);
   stmt.setObject(4, DataInicio);
   stmt.setObject(5, DataTermino);
   stmt.setString(6, Status);
   stmt.setInt(7, IdGerente);
   stmt.setInt(8, IdEquipe);
                    
   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Atualizar: " + e.getMessage());
   return false;
  }
 
} // final de EditaProjeto


 public boolean AdicionaTarefaProjeto(int idProjeto, int idUsuario, int idEquipe, String NomeTarefa, String DescricaoTarefa, LocalDateTime DataInicioTarefa, LocalDateTime  DataFinalTarefa , String StatusTarefa)
 {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String AtribuiTarefaProjetoSQL = """
  INSERT INTO TarefasProjetos(idEquipe,idUsuario) VALUES(? , ? , ? , ? , ? , ? , ? , ? )
   """;
  try (PreparedStatement stmt = connection.prepareStatement(AtribuiTarefaProjetoSQL)) {
   stmt.setInt(1, idProjeto);
   stmt.setInt(2, idUsuario);
   stmt.setInt(3, idEquipe);
   stmt.setString(4, NomeTarefa);
   stmt.setString(5, DescricaoTarefa);
   stmt.setObject(6, DataInicioTarefa);
   stmt.setObject(7, DataFinalTarefa);
   stmt.setString(8, StatusTarefa);

   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Atribuir: " + e.getMessage());
   return false;
  }
 
} // final de AdicionaTarefaProjeto


 public boolean EditaTarefaProjeto(int idProjeto, int idUsuario, int idEquipe, String NomeTarefa, String DescricaoTarefa, LocalDateTime DataInicioTarefa, LocalDateTime  DataFinalTarefa, String StatusTarefa)
 {
  if (connection == null) {
   System.err.println("Não conectado ao banco!");
   return false;
  }

  String EditaTarefaProjetoSQL = """
  UPDATE TarefasProjetos set idProjeto = ?, idUsuario = ?, idEquipe = ?, NomeTarefa = ?, DescricaoTarefa = ?, DataInicioTarefa = ?, DataFinalTarefa = ?, StatusTarefa = ? WHERE idEquipe = idEquipe AND idUsuario = idUsuario and NomeTarefa = NomeTarefa  
   """;
  try (PreparedStatement stmt = connection.prepareStatement(EditaTarefaProjetoSQL)) {
   stmt.setInt(1, idProjeto);
   stmt.setInt(2, idUsuario);
   stmt.setInt(3, idEquipe);
   stmt.setString(4, NomeTarefa);
   stmt.setString(5, DescricaoTarefa);
   stmt.setObject(6, DataInicioTarefa);
   stmt.setObject(7, DataFinalTarefa);
   stmt.setString(8, StatusTarefa);

   int linhasExecutadas = stmt.executeUpdate();
   return linhasExecutadas > 0;

  } catch (SQLException e) {
   System.err.println("Erro ao Editar: " + e.getMessage());
   return false;
  }
 
} // final de EditaTarefaProjeto


// A fazer!

// MostraUsuario
// ListaTodosUsuarios
// MostraEquipe
// ListaTodasEquipes
// MostraProjeto
// ListaTodosProjetos
// ListaUsuariosEquipe
// ListaTarefasUsuario
// ListaTarefasEquipe
// ListaTarefasProjeto




 public static void main(String[] args) {
  DatabaseManager dbManager = new DatabaseManager();
 
 System.out.println("🚀 Iniciando DatabaseManager...");
 System.out.println("📊 Verificando se banco existe: " + dbManager.bancoExiste());
 
  if (dbManager.conectar()) {
   System.out.println("✅ Conexão estabelecida com sucesso!");
   System.out.println("📋 Tabela existe: " + dbManager.tabelaUsuariosExiste());

  dbManager.desconectar();
  } else {
   System.out.println("❌ Falha na conexão/criação do banco!");
  }
 }


}
