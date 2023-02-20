package com.reddingtom.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Reddingtom
 */
public class Atividade6 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    public static void main(String[] args) {

        // Variáveis utilizadas
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql;

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");

            // Caso ela exista, dropa a tabela : users
            sql = "DROP TABLE IF EXISTS users;";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            // Cria a tabela : users
            System.out.println("Criando tabela users...");
            sql = "CREATE TABLE users ("
                    + "u_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "u_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "u_name TEXT NOT NULL,"
                    + "u_email TEXT NOT NULL,"
                    + "u_password TEXT NOT NULL,"
                    + "u_birth DATE NOT NULL,"
                    + "u_status TEXT DEFAULT 'on' CHECK (u_status IN('on', 'off', 'del'))"
                    + ");";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            // Fecha a conexão
            System.out.println("Fechando conexão com o banco de dados...");
            conn.close();

        } catch (SQLException erro) {

            // Printa na tela os erros da operação
            System.out.println("Erro ao executar a operação no banco de dados: " + erro.getMessage());

        } finally {

            // Encerra todos os recursos abertos
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
