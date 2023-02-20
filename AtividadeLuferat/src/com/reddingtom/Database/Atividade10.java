package com.reddingtom.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Reddingtom
 */
public class Atividade10 {

    // Variável de conexão com o banco
    private static final String DB_URL = "jdbc:sqlite:src\\com\\reddingtom\\Resources\\trecostest.db";

    // Define a sintaxe à qual os endereços de e-mail válidos devem aderir
    private static final String EMAIL_REGEX
            = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Senha de 4-8 caracteres exigindo números e alfabetos de ambos os casos
    private static final String PASSWORD_REGEX
            = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,12}$";

    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile(PASSWORD_REGEX);

    // Método para Validar a E-mail
    public static void emailValidator(String email) {

        Matcher matcher = EMAIL_PATTERN.matcher(email);

        if (email == null) {

            System.out.println("Ops... Por Favor, Preencha o Campo!!!");
            System.exit(0);

        } else if (matcher.matches() == true) {

            System.out.println("O Endereço de E-mail : " + email + " é Válido");

        } else {

            System.out.println("O Endereço de E-mail : " + email + " NÃO é Válido");
            System.exit(0);

        }

    }

    // Método para Validar a Senha
    public static void passwordValidator(String password) {

        Matcher matcher = PASSWORD_PATTERN.matcher(password);

        if (password == null) {

            System.out.println("Ops... Por Favor, Preencha o Campo!!!");
            System.exit(0);

        } else if (matcher.matches() == true) {

            System.out.println("A Senha : " + password + " é Válida");

        } else {

            System.out.println("A Senha : " + password + " NÃO é Válida");
            System.exit(0);

        }

    }

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
        String u_email;
        String u_password;
        Scanner scan = new Scanner(System.in);

        try {

            // Estabelece conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexão estabelecida com sucesso.");

            System.out.print("Informe a E-mail : ");
            u_email = scan.next();
            emailValidator(u_email);

            System.out.print("Informe a Senha : ");
            u_password = scan.next();
            passwordValidator(u_password);

            String sql = "SELECT * FROM users WHERE u_email = ? AND u_password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, u_email);
            stmt.setString(2, encryptPassword(u_password));

            rs = stmt.executeQuery();

            if (rs.next()) {

                System.out.println("Possui registro no banco de dados.");

                System.out.println("Nome do usuário : " + rs.getString("u_name") + ", E-mail do usuário : " + rs.getString("u_email") + ", Senha do usuário : " + rs.getString("u_password") + ", Data-de-Aniversario do usuário : " + rs.getString("u_birth"));

            } else {

                System.out.println("Não foi encontrado qualquer registros no banco de dados.");

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
