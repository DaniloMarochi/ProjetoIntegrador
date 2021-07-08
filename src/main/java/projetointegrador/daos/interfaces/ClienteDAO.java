package projetointegrador.daos.interfaces;

import projetointegrador.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    boolean inserir(Cliente cliente) throws SQLException;
    boolean atualizar(int id, Cliente cliente) throws SQLException;

    List<Cliente> lista() throws SQLException;
    Cliente buscaId(int id) throws SQLException;
    Cliente buscaClienteDaVenda(int venda_id_cliente) throws SQLException;

    boolean excluir(int id) throws SQLException;

}
