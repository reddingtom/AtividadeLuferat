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
public class Atividade9 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    public static void main(String[] args) {

        // Variáveis utilizadas
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;
        String d_name;
        String u_name;
        Integer d_uid;
        Scanner scan = new Scanner(System.in);

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");
            
            // Faz a consulta no banco de dados
            sql = "SELECT u_id FROM users WHERE u_name = ?;";
            System.out.print("Insira o nome do usuário ao qual pertence o documento : ");
            u_name = scan.next();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, u_name);
            rs = stmt.executeQuery();

            // Insire dados na tabela do documento
            System.out.print("Insira o nome do documento : ");
            d_name = scan.next();
            d_uid = rs.getInt("u_id");
            sql = "INSERT INTO documents (d_name, d_uid) VALUES (?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, d_name);
            stmt.setInt(2, d_uid);
            stmt.executeUpdate();

            // Faz a consulta no banco de dados
            sql = "SELECT * FROM documents;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Printa na tela os dados consultados da tabela : documents
            while (rs.next()) {

                System.out.println("Documento: " + rs.getString("d_name") + ", usuario: " + rs.getString("d_uid"));

            }

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
