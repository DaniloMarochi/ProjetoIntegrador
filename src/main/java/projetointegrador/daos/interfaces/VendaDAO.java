package projetointegrador.daos.interfaces;

import projetointegrador.models.Venda;

import java.sql.SQLException;
import java.util.List;

public interface VendaDAO {

    boolean inserir(Venda venda) throws SQLException;
    boolean atualizar(int id, Venda venda) throws SQLException;

    List<Venda> lista() throws SQLException;
    Venda buscaId(int id) throws SQLException;
    int ultimoId() throws SQLException;

    boolean delete(Venda venda) throws SQLException;

}
