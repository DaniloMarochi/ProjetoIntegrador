package projetointegrador.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.ls.LSOutput;
import projetointegrador.Main;
import projetointegrador.daos.JDBCArtesanatoDAO;
import projetointegrador.daos.interfaces.ArtesanatoDAO;
import projetointegrador.models.Artesanato;
import projetointegrador.models.ArtesanatoHasVenda;
import projetointegrador.models.Cliente;
import projetointegrador.models.Venda;
import projetointegrador.repositories.interfaces.ArtesanatoHasVendaRepository;
import projetointegrador.repositories.interfaces.ArtesanatoRepository;
import projetointegrador.repositories.interfaces.ClienteRepository;
import projetointegrador.repositories.interfaces.VendaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Principal extends JanelaBase{
    @FXML
    private Button btJanelaCadastraCliente;

    @FXML
    private Button btJanelaCadastraArtesanato;

    @FXML
    private Button btAdicionarVenda;

    @FXML
    private Button btRelatorio;

    @FXML
    private ListView<Cliente> lvClientes;

    @FXML
    private ListView<Artesanato> lvArtesanato;

    @FXML
    private TextField tfBuscaCliente;

    @FXML
    private TextField tfQtd_artesanato;

    @FXML
    private TextField tfValorTotal;

    @FXML
    private TextField tfBuscarArtesanato;


    private ClienteRepository clienteRepository;
    private ArtesanatoRepository artesanatoRepository;
    private ArtesanatoHasVendaRepository artesanatoHasVendaRepository;
    private VendaRepository vendaRepository;


    public Principal(ClienteRepository clienteRepository, ArtesanatoRepository artesanatoRepository, ArtesanatoHasVendaRepository artesanatoHasVendaRepository, VendaRepository vendaRepository){
        this.clienteRepository = clienteRepository;
        this.artesanatoRepository = artesanatoRepository;
        this.artesanatoHasVendaRepository = artesanatoHasVendaRepository;
        this.vendaRepository = vendaRepository;
    }


    @FXML
    private void initialize() {
        inicializaListViewsCliente();
        inicializaListViewsArtesanato();
        try{
            lvClientes.setItems(clienteRepository.lista());
            lvArtesanato.setItems(artesanatoRepository.lista());
        }catch (SQLException e){
            mostraMensagem(Alert.AlertType.ERROR, e.getMessage());
        }
    }


    private void inicializaListViewsCliente() {
        lvClientes.setCellFactory(clienteListView -> new ListCell<>() {
            @Override
            protected void updateItem(Cliente cliente, boolean b) {
                super.updateItem(cliente, b);

                if (cliente != null) {
                    setText(cliente.getNome());
                } else {
                    setText("");
                }
            }
        });
    }

    private void inicializaListViewsArtesanato() {
        lvArtesanato.setCellFactory(artesanatoListView -> new ListCell<>() {
            @Override
            protected void updateItem(Artesanato artesanato, boolean b) {
                super.updateItem(artesanato, b);

                if (artesanato != null) {
                    setText(artesanato.getCategoria());
                } else {
                    setText("");
                }
            }
        });
    }



    @FXML
    void abrirJanelaCadastraArtesanato() {
        Main.mudaCena(Main.ADICIONARARTESANATO,(aClass) -> new AdicionarArtesanato(artesanatoRepository));
    }

    @FXML
    void abrirJanelaCadastraCliente() {
        Main.mudaCena(Main.ADICIONARCLIENTE,(aClass) -> new AdicionarCliente(clienteRepository));
    }

    @FXML
    void adicionarVenda() {

        try {

            Cliente cliente = lvClientes.getSelectionModel().getSelectedItem();
            Artesanato artesanato = lvArtesanato.getSelectionModel().getSelectedItem();

            Venda venda = new Venda(Integer.parseInt(tfQtd_artesanato.getText()), artesanato.getValor()*Integer.parseInt(tfQtd_artesanato.getText()), cliente.getId(), artesanato.getId());
            tfValorTotal.setText(Float.toString(artesanato.getValor()*Integer.parseInt(tfQtd_artesanato.getText())));
            vendaRepository.adicionar(venda);

            ArtesanatoHasVenda artesanatoHasVenda = new ArtesanatoHasVenda(artesanato.getId(), vendaRepository.ultimoId());
            artesanatoHasVendaRepository.adicionar(artesanatoHasVenda);

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Venda inserida com sucesso!!");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            mostraMensagem(Alert.AlertType.ERROR, e.getMessage());
        }

        Main.voltaPrincipal();

    }


    @FXML
    void buscarArtesanato(KeyEvent event) throws SQLException{

        if(event.getCode()==KeyCode.ENTER){
            lvArtesanato.setCellFactory(artesanatoListView -> new ListCell<>() {
                @Override
                protected void updateItem(Artesanato artesanato, boolean b) {
                    super.updateItem(artesanato, b);

                    if (artesanato != null) {
                        setText(artesanato.toString());
                    } else {
                        setText("");
                    }
                }
            });

            if(tfBuscarArtesanato.getText().equals("")) {
                inicializaListViewsArtesanato();
                lvArtesanato.setItems(artesanatoRepository.lista());
            }else{
                String id_arte = tfBuscarArtesanato.getText();
                Artesanato artesanato = artesanatoRepository.buscaId(Integer.parseInt(id_arte));

                ObservableList<Artesanato> art = FXCollections.observableArrayList(artesanato);
                lvArtesanato.setItems(art);
            }
        }

    }

    @FXML
    void buscarCliente(KeyEvent event) throws SQLException{

        if(event.getCode()==KeyCode.ENTER){
            lvClientes.setCellFactory(clienteListView -> new ListCell<>() {
                @Override
                protected void updateItem(Cliente cliente, boolean b) {
                    super.updateItem(cliente, b);

                    if (cliente != null) {
                        setText(cliente.toString());
                    } else {
                        setText("");
                    }
                }
            });

            if(tfBuscaCliente.getText().equals("")) {
                inicializaListViewsCliente();
                lvClientes.setItems(clienteRepository.lista());
            }else{
                String id_client = tfBuscaCliente.getText();
                Cliente cliente = clienteRepository.buscaId(Integer.parseInt(id_client));

                ObservableList<Cliente> clt = FXCollections.observableArrayList(cliente);
                lvClientes.setItems(clt);
            }
        }
    }

    @FXML
    void emitirRelatorio() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,vendaRepository.lista().toString());
        alert.showAndWait();
    }

    @FXML
    void abrirJanelaAlterarCliente(MouseEvent event){

        if(event.getClickCount() == 2){

            Cliente cliente = lvClientes.getSelectionModel().getSelectedItem();
            if(cliente != null){
                Main.mudaCena(Main.ALTERARCLIENTE,(aClass) -> new AlterarCliente(clienteRepository,cliente));
            }
        }
    }

    @FXML
    void abrirJanelaAlterarArtesanato(MouseEvent event){

        if(event.getClickCount() == 2){

            Artesanato artesanato = lvArtesanato.getSelectionModel().getSelectedItem();
            if(artesanato != null){
                Main.mudaCena(Main.ALTERARARTESANATO,(aClass) -> new AlterarArtesanato(artesanatoRepository,artesanato));
            }
        }
    }


}

