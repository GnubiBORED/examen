package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class conexionSqlite {
    public conexionSqlite() {
    }

    /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/localhost/IdeaProjects/FXML-Example/src/main/resources/enrique_garcia_gracia.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            return conn;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return null;
    }
    public boolean INSERTAR(String TEXTO) {
        String sql = "INSERT INTO diario(TEXTO) VALUES(?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, TEXTO);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean Borrar(Integer id) {
        String sql = "DELETE FROM diario where id = (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            if (pstmt.getFetchSize() == 0){
                return false;
            } else{return true;}

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     *
     @param id id de el texto que vamos a borrar en la base de datos
     */
    public boolean Select(Integer id) {
        String sql = "select * from diario where id = (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            if (pstmt.getFetchSize() == 0){
                return false;
            } else{return true;}

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }
    /**
     *
     @param ID id de el texto que vamos a borrar en la base de datos
     */
    public boolean Update(String text,String ID) {
        String sql = "UPDATE DIARIO set texto = (?) where id = (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, text);
            pstmt.setString(2, ID);


            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }




}