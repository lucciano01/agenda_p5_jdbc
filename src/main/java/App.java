import connection.ConnectionPostgreSQL;
import dao.PessoaDAO;
import domain.Pessoa;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        var pessoaDAO = new PessoaDAO();

        var maria = new Pessoa("Maria", "23452345567");
        var jose = new Pessoa("Jose", "56234523457");

       // pessoaDAO.save(maria);
       // pessoaDAO.save(jose);
        //pessoaDAO.getAll().forEach(p -> System.out.println(p));
//        var pessoa = pessoaDAO.findById(2l);
        var pessoa = new Pessoa();
        pessoa.setId(2l);
        pessoa.setCpf("00000000000");
        pessoa.setNome("Pelé");

        pessoaDAO.updatePessoa(pessoa);


    }
}
