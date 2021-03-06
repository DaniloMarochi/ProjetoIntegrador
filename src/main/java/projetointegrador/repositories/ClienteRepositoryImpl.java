package projetointegrador.repositories;

import projetointegrador.daos.interfaces.ClienteDAO;
import projetointegrador.models.Cliente;
import projetointegrador.repositories.interfaces.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ClienteRepositoryImpl implements ClienteRepository {

    private ObservableList<Cliente> clientes;
    private ClienteDAO clienteDAO;

    public ClienteRepositoryImpl(ClienteDAO clienteDAO){
        clientes = FXCollections.observableArrayList();
        this.clienteDAO = clienteDAO;
    }

    @Override
    public boolean adicionar(Cliente cliente) throws SQLException {

        return clienteDAO.inserir(cliente);

    }

    @Override
    public boolean editar(int id, Cliente cliente) throws SQLException{

        return clienteDAO.atualizar(id,cliente);
    }

    @Override
    public ObservableList<Cliente> lista() throws SQLException {
        this.clientes.clear();
        this.clientes.addAll(clienteDAO.lista());
        return FXCollections.unmodifiableObservableList(clientes);
    }

    @Override
    public Cliente buscaId(int id) throws SQLException{
        return clienteDAO.buscaId(id);
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        return clienteDAO.excluir(id);
    }
}