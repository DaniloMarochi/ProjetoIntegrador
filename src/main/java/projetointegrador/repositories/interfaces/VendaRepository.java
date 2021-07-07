package projetointegrador.repositories.interfaces;

import projetointegrador.models.Venda;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface VendaRepository {

    boolean adicionar(Venda venda) throws SQLException;
    boolean editar(int id, Venda venda) throws SQLException;
    int ultimoId() throws SQLException;

    ObservableList<Venda> lista() throws SQLException;


}