package com.reddingtom.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Reddingtom
 */
public class Atividade8 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    public static void main(String[] args) {

        // Variáveis utilizadas
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");

            // Dropa a tabela : documents
            sql = "DROP TABLE IF EXISTS documents;";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            // Cria a tabela : documents
            sql = "CREATE TABLE documents ("
                    + "d_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "d_name TEXT NOT NULL"
                    + ");";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            // Altera a tabela : documents
            sql = "ALTER TABLE documents "
                    + "ADD COLUMN d_uid INTEGER REFERENCES users (u_id);";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            
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
