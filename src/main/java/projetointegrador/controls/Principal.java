package projetointegrador.controls;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import projetointegrador.models.Cliente;
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

    //@FXML
    //private ListView<> lvArtesanato;

    @FXML
    private TextField tfBuscaCliente;

    @FXML
    private TextField tfBuscarArtesanato;


    private ClienteRepository clienteRepository;


    public Principal(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }


    @FXML
    private void initialize() {
        inicializaListViews();

        try{
            lvClientes.setItems(clienteRepository.lista());
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
    }

    @FXML
    void abrirJanelaCadastraArtesanato() {

    }

    @FXML
    void abrirJanelaCadastraCliente() {

    }

    @FXML
    void adicionarVenda() {

    }

    @FXML
    void artesanatoSelecionado() {

    }

    @FXML
    void buscarArtesanato() {

    }

    @FXML
    void buscarCliente() {

    }

    @FXML
    void clienteSelecionado() {

    }

}

