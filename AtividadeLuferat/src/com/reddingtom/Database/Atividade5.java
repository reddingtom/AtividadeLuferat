package com.reddingtom.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author Reddingtom
 */
public class Atividade5 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    public static void main(String[] args) {

        // Variáveis utilizadas
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;
        String c_name;
        String d_name;
        Scanner scan = new Scanner(System.in);

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");

            // Dropa as tabelas caso elas existam
            sql = "DROP TABLE IF EXISTS categories;";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            sql = "DROP TABLE IF EXISTS documents;";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            
            System.out.println("Criando tabelas categories e documents...");

            // Cria as tabelas : categories, documents
            sql = "CREATE TABLE categories ("
                    + "c_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "c_name TEXT NOT NULL"
                    + ");";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            sql = "CREATE TABLE documents ("
                    + "d_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "d_name TEXT NOT NULL"
                    + ");";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            // Insire dados na tabela : categoria, documento
            System.out.print("Insira o nome da categoria : ");
            sql = "INSERT INTO categories (c_name) VALUES (?);";
            c_name = scan.next();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, c_name);
            stmt.executeUpdate();

            System.out.print("Insira o nome do documento : ");
            d_name = scan.next();
            sql = "INSERT INTO documents (d_name) VALUES (?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, d_name);
            stmt.executeUpdate();

            // Faz a consulta no banco de dados
            System.out.println("Recuperando dados das tabelas : categories, documents...");
            sql = "SELECT * FROM categories;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Printa na tela os dados consultados da tabela : categories
            while (rs.next()) {

                System.out.println("Categoria: " + rs.getString("c_name"));

            }

            // Faz a consulta no banco de dados
            sql = "SELECT * FROM documents;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Printa na tela os dados consultados da tabela : documents
            while (rs.next()) {

                System.out.println("Documento: " + rs.getString("d_name"));

            }

            // Fecha a conexão
            System.out.println("Fechando conexão com o banco de dados...");
            conn.close();

        } catch (SQLException erro) {

            // Printa na tela os erros da operação
            System.out.println("Erro ao executar a operação no banco de dados: " + erro.getMessage());

        } finally {

            // Encerra todos os recursos abertos
            try {

                if (rs != null) {

                    // Fecha a resultado do Query
                    System.out.println("Fechando os resultados do banco de dados...");
                    rs.close();

                }

            } catch (SQLException erro) {

                erro.getMessage();

            }

            try {

                if (stmt != null) {

                    // Fecha a preparação para os comandos SQL 
                    System.out.println("Fechando a preparação para os comandos SQL...");
                    stmt.close();

                }

            } catch (SQLException erro) {

                erro.getMessage();

            }

            try {

                if (conn != null) {

                    // Fecha a conexão com o banco
                    System.out.println("Fechando conexão com o banco de dados...");
                    conn.close();

                }

            } catch (SQLException erro) {

                erro.getMessage();

            }

        }

    }

}
