package projetointegrador.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projetointegrador.Main;
import projetointegrador.models.Cliente;
import projetointegrador.repositories.interfaces.ClienteRepository;

import java.sql.SQLException;

public class AdicionarCliente extends JanelaBase{

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEndereco;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfTelefone;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btAdicionarCliente;


    private ClienteRepository clienteRepository;
    private Cliente clienteOriginal;

    public AdicionarCliente(ClienteRepository clienteRepository, Cliente cliente){
        this.clienteRepository = clienteRepository;
        this.clienteOriginal = cliente;
    }

    public AdicionarCliente(ClienteRepository clienteRepository){
        this(clienteRepository,null);
    }

    @FXML
    void adicionarCliente() {
        String nome = tfNome.getText();
        String telefone = tfTelefone.getText();
        String email = tfEmail.getText();
        String endereco = tfEndereco.getText();


        if(nome.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nome inválido!!");
            alert.showAndWait();
            return;
        }

        if(telefone.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Telefone inválido!!");
            alert.showAndWait();
            return;
        }

        if(email.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Email inválido!!");
            alert.showAndWait();
            return;
        }

        if(endereco.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Endereço inválido!!");
            alert.showAndWait();
            return;
        }




        Cliente cliente = new Cliente(nome,email,telefone,endereco);

        try{
            if(clienteOriginal != null){
                clienteRepository.editar(clienteOriginal.getId(),cliente);
            }else{
                clienteRepository.adicionar(cliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
            mostraMensagem(Alert.AlertType.ERROR,e.getMessage());
        }

        Main.voltaPrincipal();
    }

    @FXML
    void voltar() {
        Main.voltaPrincipal();
    }

}

