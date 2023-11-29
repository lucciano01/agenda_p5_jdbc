package dao;

import connection.ConnectionPostgreSQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO {

    private Connection connection;

    public GenericDAO(){
        this.connection = ConnectionPostgreSQL.getConnection();
    }

    public void update(String sql, Object... parametros){
    try{
        var stmt = connection.prepareStatement(sql);
        for(int i = 0 ; i < parametros.length ; i++){
            stmt.setObject(i+1, parametros[i]);
        }
        stmt.executeQuery();
        stmt.close();

    }catch(
    SQLException ex){
        System.out.println(ex.getMessage());
    }
    }
}
