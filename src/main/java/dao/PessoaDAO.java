package dao;

import connection.ConnectionPostgreSQL;
import domain.Pessoa;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection connection;

    public PessoaDAO(){
        this.connection  = ConnectionPostgreSQL.getConnection();
    }

    public void save(Pessoa pessoa){
     String sql = "INSERT INTO PESSOA (nome, cpf) values (?,?)";
     try{
         var stmt = connection.prepareStatement(sql);
         stmt.setString(1, pessoa.getNome());
         stmt.setString(2, pessoa.getCpf());
         stmt.execute();
         stmt.close();
         System.out.println("Operação realizada com sucesso!");
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
     }
    }

    public List<Pessoa> getAll() throws SQLException {
        var pessoas = new ArrayList<Pessoa>();
        String sql = "SELECT * FROM PESSOA";

        var stmt = connection.prepareStatement(sql);
        var rs = stmt.executeQuery();
        while(rs.next()){
            var pessoa = new Pessoa(rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("cpf")
            );
            pessoas.add(pessoa);
        }
        rs.close();
        stmt.close();
        return pessoas;
    }

    public Pessoa findById(Long id) throws SQLException {
        String sql = "SELECT * FROM PESSOA WHERE ID = ?";
        var stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        var rs = stmt.executeQuery();
        //stmt.setString(2, nome);
        var pessoa = new Pessoa();
        if(rs.next()){
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setId(rs.getLong("id"));
        }
        rs.close();
        stmt.close();
       return pessoa;

    }
}
