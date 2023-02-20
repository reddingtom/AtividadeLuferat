package com.reddingtom.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class Atividade7 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    // Método para criptografar a senha com SHA1
    private static String encryptPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {

                sb.append(String.format("%02x", b));

            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {

            e.getMessage();
            return null;

        }

    }

    public static void main(String[] args) {

        // Variáveis utilizadas
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql;
        String u_name;
        String u_email;
        String u_password;
        String u_birth;
        String c_name;
        Scanner scan = new Scanner(System.in);

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");
            
            // Faz a consulta no banco de dados
            sql = "SELECT c_id FROM categories WHERE c_name = ?;";
            System.out.print("Insira o nome da categoria ao qual pertence o usuário : ");
            c_name = scan.next();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, c_name);
            rs = stmt.executeQuery();

            // Insire dados na tabela : users
            System.out.print("Insira o nome do usuário : ");
            u_name = scan.next();
            System.out.print("Insira o e-mail do usuário : ");
            u_email = scan.next();
            System.out.print("Insira a senha do usuário : ");
            u_password = scan.next();
            System.out.print("Insira a data-de-aniversario do usuário : ");
            u_birth = scan.next();
            sql = "INSERT INTO users (u_name, u_email, u_password, u_birth) VALUES (?, ?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, u_name);
            stmt.setString(2, u_email);
            stmt.setString(3, encryptPassword(u_password));
            stmt.setString(4, u_birth);
            stmt.executeUpdate();

            // Faz a consulta no banco de dados
            System.out.println("Recuperando dado da tabela : users...");
            sql = "SELECT * FROM users;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                System.out.println("Nome do usuário : " + rs.getString("u_name") + ", E-mail do usuário : " + rs.getString("u_email") + ", Senha do usuário : " + rs.getString("u_password") + ", Data-de-Aniversario do usuário : " + rs.getString("u_birth"));

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
