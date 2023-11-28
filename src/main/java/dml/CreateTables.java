package dml;

import connection.ConnectionPostgreSQL;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTables {

    private Connection connection;

    public CreateTables(){
        this.connection = ConnectionPostgreSQL.getConnection();
    }

    public void createTablePessoa() {
        String sql;
        try{
            sql = "CREATE TABLE IF NOT EXISTS PESSOA (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(255) NOT NULL, " +
                    "cpf VARCHAR(11) NOT NULL " +
                    ")";
            var stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("Tabela pessoa criada com sucesso!");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }
}
