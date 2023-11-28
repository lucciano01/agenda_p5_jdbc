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
        System.out.println(pessoaDAO.findById(2l));


    }
}
