package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPostgreSQL {

    private static final String URL =  "jdbc:postgresql://localhost:5432/agenda";
    private static final String USER =  "postgres";
    private static final String PASSWORD =  "123456";

    public static Connection getConnection(){
        Connection connection = null;
        try{
           connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexao realizda com sucesso!");
        }catch(SQLException ex){
            System.out.println("Erro ao conectar na BD!" + ex.getMessage());
        }

        return connection;


    }

}
