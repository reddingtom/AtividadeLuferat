package trecos.exercicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import trecos.exercicios.database.DbConnection;

public class Documents {

    // Faz conexão DAO
    private DbConnection dbConnection = new DbConnection();

    // Inicializa atributos DAO
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet res = null;
    private String sql = null;

    // Retorna a idade de acordo com a data de nascimento
    public int getAge(String nasc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(nasc, formatter);
        LocalDate date = LocalDate.now();
        Period period = Period.between(birthDate, date);
        
        return period.getYears();
    }

    // Visualiza todos os documentos
    public void showAllDocs() {
        try {
            // Comando SQL
            sql = "SELECT * FROM documents INNER JOIN categories ON dcategory = cid WHERE dstatus = 'on';";

            // Conectando ao banco de dados
            conn = dbConnection.dbConnect();

            // Executando o comando SQL no banco de dados e armazenando o retorno na variável
            pstm = conn.prepareStatement(sql);
            res = pstm.executeQuery();

            // Looping para pegar os valores armazenados na variável
            while (res.next()) {
                System.out.println("ID: " + res.getInt("did"));
                System.out.println("    Date: " + res.getString("ddate"));
                System.out.println("    Category: " + res.getString("cname"));
                System.out.println("    Name: " + res.getString("dname"));
                System.out.println("    Content: " + res.getString("dcontent"));
                System.out.println("");
            }

        } catch (SQLException error) {
            // Caso gere um erro
            JOptionPane.showMessageDialog(null, "Documents.showAllDocs\n" + error, "Oooops!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        } finally {
            // Finaliza a conexão com o banco de dados
            dbConnection.dbClose(conn, pstm, res);
            sql = null;
        }
    }
    
    // Visualiza o documento de acordo com o ID do usuário
    public void showDoc(int userId) {
        try {
            // Comando SQL
            sql = "SELECT did, dname, dcategory FROM documents INNER JOIN categories ON dcategory = cid WHERE duser = ? AND dstatus = 'on'";

            // Conectando ao banco de dados
            conn = dbConnection.dbConnect();

            // Executando o comando SQL no banco de dados e armazenando o retorno na variável
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userId);
            res = pstm.executeQuery();

            // Looping para pegar os valores armazenados na variável
            while (res.next()) {
                System.out.println("ID: " + res.getInt("did"));
                System.out.println("    Name: " + res.getString("dname"));
                System.out.println("    Category: " + res.getString("cname"));
                System.out.println("");
            }

        } catch (SQLException error) {
            // Caso gere um erro
            JOptionPane.showMessageDialog(null, "Documents.showDoc\n" + error, "Oooops!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        } finally {
            // Finaliza a conexão com o banco de dados
            dbConnection.dbClose(conn, pstm, res);
            sql = null;
        }
    }

    // cria um novo usuário no banco de dados
    public void createUser(String uName, String uEmail, String uPassword, String uDate) {

        if (Validator.isEmail(uEmail) && Validator.isPassword(uPassword)) {
            try {
                // Comando SQL
                sql = "INSERT INTO users(uname, uemail, upassword, udate) VALUES (?, ?, sha1(?), ?)";

                // Conectando ao banco de dados
                conn = dbConnection.dbConnect();

                // Executando o comando SQL no banco de dados
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, uName);
                pstm.setString(2, uEmail);
                pstm.setString(3, uPassword);
                pstm.setString(4, uDate);
                pstm.execute();

                // Exibe uma notificação ao usuário
                JOptionPane.showMessageDialog(null, "Sucesso! Novo Usuário Criado...", "Oba!", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException error) {
                // Caso gere um erro
                JOptionPane.showMessageDialog(null, "Documents.createUser\n" + error, "Oooops!", JOptionPane.ERROR_MESSAGE);
                System.exit(0);

            } finally {
                // Finaliza a conexão com o banco de dados
                dbConnection.dbClose(conn, pstm, res);
                sql = null;
            }

        } else {
            // Caso o Email ou a Senha Não sejam validos
            JOptionPane.showMessageDialog(null, "Email e/ou Senha incorretos", "Oooops!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Cria um novo documento no banco de dados
    public void createDocs(int dCategory, int dUser, String dName, String dContent) {
        try {
            // Comando SQL
            sql = "INSERT INTO documents(dcategory, duser, dname, dcontent) VALUES (?, ?, ?, ?)";

            // Conectando ao banco de dados
            conn = dbConnection.dbConnect();

            // Executando o comando SQL no banco de dados
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, dCategory);
            pstm.setInt(2, dUser);
            pstm.setString(3, dName);
            pstm.setString(4, dContent);
            pstm.execute();

            // Exibe uma notificação ao usuário
            JOptionPane.showMessageDialog(null, "Sucesso! Novo Documento Criado...", "Oba!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            // Caso gere um erro
            JOptionPane.showMessageDialog(null, "Documents.createDocs\n" + error, "Oooops!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        } finally {
            // Finaliza a conexão com o banco de dados
            dbConnection.dbClose(conn, pstm, res);
            sql = null;
        }
    }

    // Cria uma nova categoria no banco de dados
    public void createCategory(String categoryName) {
        try {
            // Comando SQL
            sql = "INSERT INTO categories(cname) VALUES (?)";

            // Conectando ao banco de dados
            conn = dbConnection.dbConnect();

            // Executando o comando SQL no banco de dados
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, categoryName);
            pstm.execute();

            // Exibe uma notificação ao usuário
            JOptionPane.showMessageDialog(null, "Sucesso! Nova categoria Criada...", "Oba!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            // Caso gere um erro
            JOptionPane.showMessageDialog(null, "Documents.createCategory\n" + error, "Oooops!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        } finally {
            // Finaliza a conexão com o banco de dados
            dbConnection.dbClose(conn, pstm, res);
            sql = null;
        }
    }

    public static void main(String[] args) {
        new Documents().createCategory("Pão duro");
        new Documents().createDocs(4, 1, "Coxinha Azeda", "Lorem ipsum...");
    }
}
