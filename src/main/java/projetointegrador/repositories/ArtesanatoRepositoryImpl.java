package projetointegrador.repositories;

import projetointegrador.daos.interfaces.ArtesanatoDAO;
import projetointegrador.models.Artesanato;
import projetointegrador.models.Cliente;
import projetointegrador.repositories.interfaces.ArtesanatoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ArtesanatoRepositoryImpl implements ArtesanatoRepository {

    private ObservableList<Artesanato> artesanatos;
    private ArtesanatoDAO artesanatoDAO;

    public ArtesanatoRepositoryImpl(ArtesanatoDAO artesanatoDAO){
        artesanatos = FXCollections.observableArrayList();
        this.artesanatoDAO = artesanatoDAO;
    }

    @Override
    public boolean adicionar(Artesanato artesanato) throws SQLException {

        return artesanatoDAO.inserir(artesanato);

    }

    @Override
    public boolean editar(int id, Artesanato artesanato) throws SQLException{

        return artesanatoDAO.atualizar(id,artesanato);
    }

    @Override
    public ObservableList<Artesanato> lista() throws SQLException {
        this.artesanatos.clear();
        this.artesanatos.addAll(artesanatoDAO.lista());
        return FXCollections.unmodifiableObservableList(artesanatos);
    }

    @Override
    public Artesanato buscaId(int id) throws SQLException{
        return artesanatoDAO.buscaId(id);
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        return artesanatoDAO.excluir(id);
    }
}