package projetointegrador.repositories;

import projetointegrador.daos.interfaces.VendaDAO;
import projetointegrador.models.Venda;
import projetointegrador.repositories.interfaces.VendaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class VendaRepositoryImpl implements VendaRepository {

    private ObservableList<Venda> vendas;
    private VendaDAO vendaDAO;

    public VendaRepositoryImpl(VendaDAO vendaDAO){
        vendas = FXCollections.observableArrayList();
        this.vendaDAO = vendaDAO;
    }

    @Override
    public boolean adicionar(Venda venda) throws SQLException {

        return vendaDAO.inserir(venda);

    }

    @Override
    public boolean editar(int id, Venda venda) throws SQLException{

        return vendaDAO.atualizar(id,venda);
    }

    @Override
    public ObservableList<Venda> lista() throws SQLException {
        this.vendas.clear();
        this.vendas.addAll(vendaDAO.lista());
        return FXCollections.unmodifiableObservableList(vendas);
    }

    @Override
    public int ultimoId() throws SQLException {
        return vendaDAO.ultimoId();
    }
}