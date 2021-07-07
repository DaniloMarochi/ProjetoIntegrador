package projetointegrador.repositories.interfaces;

import projetointegrador.models.Artesanato;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ArtesanatoRepository {

    boolean adicionar(Artesanato artesanato) throws SQLException;
    boolean editar(int id, Artesanato artesanato) throws SQLException;

    ObservableList<Artesanato> lista() throws SQLException;
    Artesanato buscaId(int id) throws SQLException;


}
