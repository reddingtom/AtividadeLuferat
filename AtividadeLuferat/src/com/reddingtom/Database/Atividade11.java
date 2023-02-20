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
public class Atividade11 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    public static void main(String[] args) {

        // Variáveis utilizadas
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;
        Integer d_uid;
        Scanner scan = new Scanner(System.in);

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");

            // Faz a consulta no banco de dados
            System.out.print("ID : ");
            d_uid = scan.nextInt();
            System.out.println("Recuperando dado da tabela users...");
            sql = "SELECT documents.d_id, documents.d_name, categories.c_name FROM documents, categories "
                    + "WHERE documents.d_uid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, d_uid);
            rs = stmt.executeQuery();

            // Printa na tela os dados consultados
            while (rs.next()) {

                System.out.println("Documentos - ID: " + rs.getInt("d_id") + ", Nome: " + rs.getString("d_name") + " | Categoria: " + rs.getString("c_name"));
                
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
