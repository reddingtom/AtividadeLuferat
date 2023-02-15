package trecos.exercicios.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DbConnection {

    private Connection conn = null;
    private final String SQLITEURL = "jdbc:mysql://localhost:3306/trecostest?user=root&password=";

    // Método de conexão com o banco de dados
    public Connection dbConnect() {
        try {
            // Inicia a conexão usando a URL
            conn = DriverManager.getConnection(SQLITEURL);
            
        } catch (SQLException error) {
            // Caso gere um erro
            JOptionPane.showMessageDialog(null, "DbConnection.dbConnect\n" + error, "Oooops!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Retorna a conexão estabelecida
        return conn;
    }

    // Método que encerra todos os recursos abertos
    public void dbClose(Connection conn, PreparedStatement pstm, ResultSet res) {
        if (res != null) try {
            res.close();
        } catch (SQLException ignore) {}

        if (pstm != null) try {
            pstm.close();
        } catch (SQLException ignore) {}

        if (conn != null) try {
            conn.close();
        } catch (SQLException ignore) {}
    }
}