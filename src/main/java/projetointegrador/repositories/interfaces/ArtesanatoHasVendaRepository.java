package projetointegrador.repositories.interfaces;

import javafx.collections.ObservableList;
import projetointegrador.models.Artesanato;
import projetointegrador.models.ArtesanatoHasVenda;

import java.sql.SQLException;

public interface ArtesanatoHasVendaRepository {

    boolean adicionar(ArtesanatoHasVenda artesanatoHasVenda) throws SQLException;

    ObservableList<ArtesanatoHasVenda> lista() throws SQLException;

}
