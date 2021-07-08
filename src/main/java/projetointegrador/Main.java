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
    public static final String ALTERARCLIENTE = "/fxml/alterar_cliente.fxml";
    public static final String ALTERARARTESANATO = "/fxml/alterar_artesanato.fxml";


    private static ClienteRepository clienteRepository;
    private static ArtesanatoRepository artesanatoRepository;
    private static ArtesanatoHasVendaRepository artesanatoHasVendaRepository;
    private static VendaRepository vendaRepository;


    private static ClienteDAO clienteDAO;
    private static ArtesanatoDAO artesanatoDAO;
    private static ArtesanatoHasVendaDAO artesanatoHasVendaDAO;
    private static VendaDAO vendaDAO;

    private static StackPane base;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();

        clienteDAO = new JDBCClienteDAO();
        artesanatoDAO = new JDBCArtesanatoDAO();
        artesanatoHasVendaDAO = new JDBCArtesanatoHasVendaDAO();
        vendaDAO = new JDBCVendaDAO();


        clienteRepository = new ClienteRepositoryImpl(clienteDAO);
        artesanatoRepository = new ArtesanatoRepositoryImpl(artesanatoDAO);
        vendaRepository = new VendaRepositoryImpl(vendaDAO);
        artesanatoHasVendaRepository = new ArtesanatoHasVendaRepositoryImpl(artesanatoHasVendaDAO, vendaDAO, clienteDAO, artesanatoDAO);

    }

    @Override
    public void start(Stage stage){
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
        return (aClass) -> new Principal(clienteRepository, artesanatoRepository, artesanatoHasVendaRepository, vendaRepository);
    }

}
