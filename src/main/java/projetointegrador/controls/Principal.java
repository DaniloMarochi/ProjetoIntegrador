package projetointegrador.controls;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        inicializaListViews();
        try{
            lvClientes.setItems(clienteRepository.lista());
            lvArtesanato.setItems(artesanatoRepository.lista());
        }catch (SQLException e){
            mostraMensagem(Alert.AlertType.ERROR, e.getMessage());
        }
    }


    private void inicializaListViews() {
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
    void buscarArtesanato() {

    }

    @FXML
    void buscarCliente() {

        /*if(event.getKeyCode()==KeyEvent.VK_ENTER){
            String client = tfBuscarArtesanato.getText();

            lvClientes.setCellFactory(clienteListView -> new ListCell<>() {
                @Override
                protected void updateItem(Cliente cliente, boolean b) {
                    super.updateItem(cliente, b);

                    if (cliente != null) {
                        setText(client);
                    } else {
                        setText("");
                    }
                }
            });

        }*/
    }

    @FXML
    void emitirRelatorio() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,vendaRepository.lista().toString());
        alert.showAndWait();
    }


}

