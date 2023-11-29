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
    private GenericDAO genericDAO;

    public PessoaDAO(){
        this.connection  = ConnectionPostgreSQL.getConnection();
        this.genericDAO = new GenericDAO();
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
    public void update(Pessoa pessoa){
       try{
           String sql = "UPDATE PESSOA " +
                   "SET NOME = ?, CPF = ?" +
                   "WHERE ID = ? ";
           var stmt = connection.prepareStatement(sql);
           stmt.setObject(1, pessoa.getNome());
           stmt.setObject(2, pessoa.getCpf());
           stmt.setObject(3, pessoa.getId());
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }

    }

    public void updatePessoa(Pessoa pessoa) {
        try {
            String sql = "UPDATE PESSOA " +
                    "SET NOME = ?, CPF = ?" +
                    "WHERE ID = ? ";
            var stmt = connection.prepareStatement(sql);
            genericDAO.update(sql, pessoa.getNome(),
                    pessoa.getCpf(), pessoa.getId());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) throws SQLException {
      try{
          String sql = "DELETE FROM PESSOA WHERE ID = ?";
          var stmt = connection.prepareStatement(sql);
          stmt.setLong(1, id);
          stmt.execute();
          stmt.close();
          System.out.println("Usuario removido com sucesso!");
      }catch(SQLException ex){
          System.out.println("Erro ao deletar usuario " +ex.getMessage());
      }

    }
}
