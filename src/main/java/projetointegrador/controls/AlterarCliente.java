package projetointegrador.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projetointegrador.Main;
import projetointegrador.models.Cliente;
import projetointegrador.repositories.interfaces.ClienteRepository;

import java.sql.SQLException;

public class AlterarCliente extends JanelaBase{

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
    private Button btAlterarCliente;

    @FXML
    private Button btExcluir;

    private ClienteRepository clienteRepository;
    private Cliente clienteOriginal;

    public AlterarCliente(ClienteRepository clienteRepository, Cliente cliente){
        this.clienteRepository = clienteRepository;
        this.clienteOriginal = cliente;
    }

    public AlterarCliente(ClienteRepository clienteRepository){
        this(clienteRepository,null);
    }


    @FXML
    void initialize(){
        tfNome.setText(clienteOriginal.getNome());
        tfEmail.setText(clienteOriginal.getEmail());
        tfTelefone.setText(clienteOriginal.getTelefone());
        tfEndereco.setText(clienteOriginal.getEndereco());
    }

    @FXML
    void alterarCliente() {


        if(tfNome.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nome inválido!!");
            alert.showAndWait();
            return;
        }

        if(tfTelefone.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Telefone inválido!!");
            alert.showAndWait();
            return;
        }

        if(tfEmail.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Email inválido!!");
            alert.showAndWait();
            return;
        }

        if(tfEndereco.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Endereço inválido!!");
            alert.showAndWait();
            return;
        }


        Cliente cliente = new Cliente(tfNome.getText(), tfEmail.getText(), tfTelefone.getText(), tfEndereco.getText());

        try{
            if(clienteOriginal != null){
                clienteRepository.editar(clienteOriginal.getId(),cliente);
            }else{
                clienteRepository.adicionar(cliente);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Cliente alterado com sucesso!!");
                alert.showAndWait();
            }
        }catch (SQLException e){
            e.printStackTrace();
            mostraMensagem(Alert.AlertType.ERROR,e.getMessage());
        }

        Main.voltaPrincipal();
    }

    @FXML
    void deletarCliente() {

    }

    @FXML
    void voltar() {
        Main.voltaPrincipal();
    }
}
