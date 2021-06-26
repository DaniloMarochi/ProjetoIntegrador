package projetointegrador.repositories.interfaces;

import projetointegrador.models.Cliente;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ClienteRepository {

    boolean adicionar(Cliente cliente) throws SQLException;
    boolean editar(int id, Cliente cliente) throws SQLException;

    ObservableList<Cliente> lista() throws SQLException;


}
