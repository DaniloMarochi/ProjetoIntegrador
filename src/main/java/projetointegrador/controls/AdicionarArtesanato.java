package projetointegrador.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import projetointegrador.Main;
import projetointegrador.models.Artesanato;
import projetointegrador.models.Cliente;
import projetointegrador.repositories.interfaces.ArtesanatoRepository;
import projetointegrador.repositories.interfaces.ClienteRepository;

import java.sql.SQLException;

public class AdicionarArtesanato extends JanelaBase{

    @FXML
    private RadioButton rbPequeno;

    @FXML
    private RadioButton rbMedio;

    @FXML
    private RadioButton rbGrande;

    @FXML
    private TextField tfMaterial;

    @FXML
    private TextField tfCategoria;

    @FXML
    private TextField tfValor;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btAdicionarArtesanato;

    private ArtesanatoRepository artesanatoRepository;
    private Artesanato artesanatoOriginal;

    public AdicionarArtesanato(ArtesanatoRepository artesanatoRepository, Artesanato artesanato){
        this.artesanatoRepository = artesanatoRepository;
        this.artesanatoOriginal = artesanato;
    }

    public AdicionarArtesanato(ArtesanatoRepository artesanatoRepository){
        this(artesanatoRepository,null);
    }

    @FXML
    void adicionarArtesanato() {

        String tamanho=null;

        if(rbPequeno.isSelected()) {
            tamanho = rbPequeno.getText();
        }else if(rbMedio.isSelected()){
            tamanho = rbMedio.getText();
        }else if(rbGrande.isSelected()){
            tamanho = rbGrande.getText();
        }
        String material = tfMaterial.getText();
        String categoria = tfCategoria.getText();
        float valor=-1;
        int estoque=-1;

        try{
            valor = Float.valueOf(tfValor.getText());

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido!!");
            alert.showAndWait();
            return;
        }


        if(tamanho.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Tamanho inválido!!");
            alert.showAndWait();
            return;
        }

        if(material.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Material inválido!!");
            alert.showAndWait();
            return;
        }

        if(categoria.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Categoria inválida!!");
            alert.showAndWait();
            return;
        }

        try{
            estoque = Integer.valueOf(tfQuantidade.getText());

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Quantidade inválida!!");
            alert.showAndWait();
            return;
        }




        Artesanato artesanato = new Artesanato(tamanho, material, categoria, valor, estoque);

        try{
            if(artesanatoOriginal != null){
                artesanatoRepository.editar(artesanatoOriginal.getId(),artesanato);
            }else{
                artesanatoRepository.adicionar(artesanato);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Artesanato inserido com sucesso!!");
                alert.showAndWait();
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

