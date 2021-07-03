package projetointegrador.controls;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import projetointegrador.Main;
import projetointegrador.models.Artesanato;
import projetointegrador.models.Cliente;
import projetointegrador.repositories.interfaces.ArtesanatoRepository;
import projetointegrador.repositories.interfaces.ClienteRepository;

import java.sql.SQLException;

public class Principal extends JanelaBase{

    @FXML
    private Button btJanelaCadastraCliente;

    @FXML
    private Button btJanelaCadastraArtesanato;

    @FXML
    private Button btAdicionarVenda;

    @FXML
    private ListView<Cliente> lvClientes;

    @FXML
    private ListView<Artesanato> lvArtesanato;

    @FXML
    private TextField tfBuscaCliente;

    @FXML
    private TextField tfBuscarArtesanato;


    private ClienteRepository clienteRepository;
    private ArtesanatoRepository artesanatoRepository;


    public Principal(ClienteRepository clienteRepository, ArtesanatoRepository artesanatoRepository){
        this.clienteRepository = clienteRepository;
        this.artesanatoRepository = artesanatoRepository;
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

    }

    @FXML
    void artesanatoSelecionado() {

    }

    @FXML
    void clienteSelecionado() {

    }

    @FXML
    void buscarArtesanato() {

    }

    @FXML
    void buscarCliente() {

    }


}

