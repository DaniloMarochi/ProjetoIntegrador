package projetointegrador.daos.interfaces;

import projetointegrador.models.ArtesanatoHasVenda;

import java.sql.SQLException;
import java.util.List;

public interface ArtesanatoHasVendaDAO {

    boolean inserir(ArtesanatoHasVenda artesanatoHasVenda) throws SQLException;

    ArtesanatoHasVenda buscaId(int id) throws SQLException;
    List<ArtesanatoHasVenda> lista() throws SQLException;

}
