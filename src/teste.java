import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class teste {
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/crediapp";
    private static final String USUARIO = "crediApp";
    private static final String SENHA = "3147796943bg";

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch(SQLException e) {
            System.out.println("Erro ao tentar conectar com o banco." + e.getMessage());
            return null;
        }
    }
}
