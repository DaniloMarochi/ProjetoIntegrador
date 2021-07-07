package projetointegrador.repositories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projetointegrador.daos.interfaces.ArtesanatoDAO;
import projetointegrador.daos.interfaces.ArtesanatoHasVendaDAO;
import projetointegrador.daos.interfaces.ClienteDAO;
import projetointegrador.daos.interfaces.VendaDAO;
import projetointegrador.models.Artesanato;
import projetointegrador.models.ArtesanatoHasVenda;
import projetointegrador.models.Venda;
import projetointegrador.repositories.interfaces.ArtesanatoHasVendaRepository;

import java.sql.SQLException;
import java.util.List;

public class ArtesanatoHasVendaRepositoryImpl implements ArtesanatoHasVendaRepository {

    private ObservableList<Artesanato> artesanatos;
    private ObservableList<ArtesanatoHasVenda> artesanatoHasVendas;

    private ArtesanatoHasVendaDAO artesanatoHasVendaDAO;
    private VendaDAO vendaDAO;
    private ClienteDAO clienteDAO;
    private ArtesanatoDAO artesanatoDAO;


    public ArtesanatoHasVendaRepositoryImpl( ArtesanatoHasVendaDAO artesanatoHasVendaDAO,
                                VendaDAO vendaDAO,
                                ClienteDAO clienteDAO,
                                ArtesanatoDAO artesanatoDAO){

        this.artesanatoHasVendaDAO = artesanatoHasVendaDAO;
        this.vendaDAO = vendaDAO;
        this.clienteDAO = clienteDAO;
        this.artesanatoDAO = artesanatoDAO;

        artesanatos = FXCollections.observableArrayList();
        artesanatoHasVendas = FXCollections.observableArrayList();
    }


    @Override
    public boolean adicionar(ArtesanatoHasVenda artesanatoHasVenda) throws SQLException {
        return artesanatoHasVendaDAO.inserir(artesanatoHasVenda);
    }


    @Override
    public ObservableList<ArtesanatoHasVenda> lista() throws SQLException{

        this.artesanatoHasVendas.clear();

        List<ArtesanatoHasVenda> lista = artesanatoHasVendaDAO.lista();

        for(ArtesanatoHasVenda p:lista){
            //p.setCliente(clienteDAO.buscaClienteDaVenda(p.getId()));
            //p.setArtesanato(artesanatoDAO.buscaArtesanatoDaVenda(p.getId()));
        }


        this.artesanatoHasVendas.addAll(lista);


        return FXCollections.unmodifiableObservableList(artesanatoHasVendas);
    }
}
