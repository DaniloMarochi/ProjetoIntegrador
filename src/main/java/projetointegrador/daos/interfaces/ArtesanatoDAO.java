package projetointegrador.daos.interfaces;

import projetointegrador.models.Artesanato;
import projetointegrador.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ArtesanatoDAO {

    boolean inserir(Artesanato artesanato) throws SQLException;
    boolean atualizar(int id, Artesanato artesanato) throws SQLException;

    List<Artesanato> lista() throws SQLException;
    Artesanato buscaId(int id) throws SQLException;

    boolean delete(Artesanato artesanato) throws SQLException;

}
