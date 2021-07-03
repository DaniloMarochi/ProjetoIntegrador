package projetointegrador;

import projetointegrador.daos.*;
import projetointegrador.daos.interfaces.*;
import projetointegrador.controls.Principal;
import projetointegrador.models.*;
import projetointegrador.repositories.*;
import projetointegrador.repositories.interfaces.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;

public class Main extends Application {

    public static final String PRINCIPAL = "/fxml/principal.fxml";
    public static final String ADICIONARCLIENTE = "/fxml/adicionar_cliente.fxml";
    public static final String ADICIONARARTESANATO = "/fxml/adicionar_artesanato.fxml";
    //public static final String ADICIONARBEBIDA = "/fxml/adicionar_bebida.fxml";
    //public static final String ADICIONARACOMPANHAMENTO = "/fxml/adicionar_acompanhamento.fxml";
    //public static final String ADICIONARPEDIDO = "/fxml/adicionar_pedido.fxml";


    private static ClienteRepository clienteRepository;
    private static ArtesanatoRepository artesanatoRepository;


    private static ClienteDAO clienteDAO;
    private static ArtesanatoDAO artesanatoDAO;


    private static StackPane base;


    private static void criaFakes() {
        try {
            clienteRepository.adicionar(new Cliente("Cliente 1", "cliente@cliente1", "12341234", "endereco123,123"));
            clienteRepository.adicionar(new Cliente("Cliente 2", "cliente@cliente2", "12341235", "endereco124,124"));
            clienteRepository.adicionar(new Cliente("Cliente 3", "cliente@cliente3", "12341236", "endereco125,125"));
            clienteRepository.adicionar(new Cliente("Cliente 4", "cliente@cliente4", "12341237", "endereco126,126"));
            clienteRepository.adicionar(new Cliente("Cliente 5", "cliente@cliente5", "12341238", "endereco127,127"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();

        clienteDAO = new JDBCClienteDAO();
        artesanatoDAO = new JDBCArtesanatoDAO();


        clienteRepository = new ClienteRepositoryImpl(clienteDAO);
        artesanatoRepository = new ArtesanatoRepositoryImpl(artesanatoDAO);

        //Criando objetos temporários para teste
        //criaFakes();
    }

    @Override
    public void start(Stage stage) throws Exception {
        base = new StackPane();


        stage.setScene(new Scene(base, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE));
        stage.setTitle("Smart art");


        mudaCena(Main.PRINCIPAL, principalCallback());

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void mudaCena(String fxml, Callback controllerFactory) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxml));
            loader.setControllerFactory(controllerFactory);

            Parent novoRoot = loader.load();

            if (base.getChildren().stream().count() > 0) {
                base.getChildren().remove(0);
            }
            base.getChildren().add(novoRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void voltaPrincipal() {
        mudaCena(Main.PRINCIPAL, principalCallback());
    }

    private static Callback principalCallback() {
        return (aClass) -> new Principal(clienteRepository, artesanatoRepository);
    }

}
