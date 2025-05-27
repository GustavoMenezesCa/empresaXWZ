/*
package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    private static final String DATABASE_NAME = "empresaXWZ_Prod"; // SEU BANCO DE DADOS
    private static final String URL_BASE = "jdbc:postgresql://localhost:5432/";
    private static final String URL_DB = URL_BASE + DATABASE_NAME;
    private static final String USER = "postgres"; // SEU USUÁRIO
    private static final String PASSWORD = "123456"; // SUA SENHA

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL não encontrado! Verifique o pom.xml.", e);
        }
    }

    */
/**
     * Obtém uma conexão com o banco de dados configurado.
     * @return uma instância de Connection.
     * @throws SQLException se ocorrer um erro ao obter a conexão.
     *//*

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_DB, USER, PASSWORD);
    }

    */
/**
     * Método main para teste rápido e individual da conexão com o banco de dados.
     * Não executa setup de tabelas.
     *//*

    public static void main(String[] args) {
        System.out.println("--- Testando Conexão com o Banco de Dados ---");
        System.out.println("Tentando conectar a: " + URL_DB + " com usuário: " + USER);

        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("------------------------------------------------------");
                System.out.println("Conexão com PostgreSQL estabelecida com SUCESSO!");
                System.out.println("  URL: " + connection.getMetaData().getURL());
                System.out.println("  Usuário: " + connection.getMetaData().getUserName());
                System.out.println("------------------------------------------------------");
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT version(), current_database(), current_user;")) {
                    if (rs.next()) {
                        System.out.println("  Versão do Servidor: " + rs.getString(1));
                        System.out.println("  Banco de Dados Atual: " + rs.getString(2));
                        System.out.println("  Usuário no Banco: " + rs.getString(3));
                        System.out.println("------------------------------------------------------");
                    }
                }
            } else {
                System.err.println("FALHA ao conectar. Conexão nula ou fechada.");
            }
        } catch (SQLException e) {
            System.err.println("------------------------------------------------------");
            System.err.println("ERRO DE SQL ao tentar conectar:");
            System.err.println("  Mensagem: " + e.getMessage());
            System.err.println("  SQLState: " + e.getSQLState());
            System.err.println("  ErrorCode: " + e.getErrorCode());
            System.err.println("  Verifique URL, nome do banco, usuário, senha e se o servidor PostgreSQL está rodando.");
            System.err.println("------------------------------------------------------");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("ERRO GERAL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}*/
