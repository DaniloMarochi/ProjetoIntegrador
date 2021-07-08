package projetointegrador.controls;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import projetointegrador.Main;
import projetointegrador.models.Artesanato;
import projetointegrador.repositories.interfaces.ArtesanatoRepository;

import java.sql.SQLException;

public class AlterarArtesanato extends JanelaBase{
    @FXML
    private RadioButton rbPequeno;

    @FXML
    private ToggleGroup tamanho;

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
    private Button btAlterarArtesanato;

    @FXML
    private Button btExcluir;

    private ArtesanatoRepository artesanatoRepository;
    private Artesanato artesanatoOriginal;

    public AlterarArtesanato(ArtesanatoRepository artesanatoRepository, Artesanato artesanato){
        this.artesanatoRepository = artesanatoRepository;
        this.artesanatoOriginal = artesanato;
    }

    public AlterarArtesanato(ArtesanatoRepository artesanatoRepository){
        this(artesanatoRepository,null);
    }

    @FXML
    void initialize(){

        String tamanho = artesanatoOriginal.getTamanho();

        if(tamanho.equals(rbPequeno.getText())) {
            rbPequeno.fire();
        }else if(tamanho.equals(rbMedio.getText())){
            rbMedio.fire();
        }else if(tamanho.equals(rbGrande.getText())){
            rbGrande.fire();
        }
        tfMaterial.setText(artesanatoOriginal.getMaterial());
        tfCategoria.setText(artesanatoOriginal.getCategoria());
        tfValor.setText(Float.toString(artesanatoOriginal.getValor()));
        tfQuantidade.setText(Integer.toString(artesanatoOriginal.getEstoque()));

    }

    @FXML
    void alterarArtesanato() {
        String tmn = null;

        if(rbPequeno.isSelected()) {
            tmn = rbPequeno.getText();
        }else if(rbMedio.isSelected()){
            tmn = rbMedio.getText();
        }else if(rbGrande.isSelected()){
            tmn = rbGrande.getText();
        }

        try{
            if(tfValor!=null) {
                tfValor.setText(tfValor.getText());
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido!!");
            alert.showAndWait();
            return;
        }


        if(tfMaterial.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Material inválido!!");
            alert.showAndWait();
            return;
        }

        if(tfCategoria.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Categoria inválida!!");
            alert.showAndWait();
            return;
        }

        try{
            if(tfValor!=null) {
                tfQuantidade.setText(tfQuantidade.getText());
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Quantidade inválida!!");
            alert.showAndWait();
            return;
        }

        Artesanato artesanato = new Artesanato(tmn, tfMaterial.getText(), tfCategoria.getText(), Float.parseFloat(tfValor.getText()), Integer.parseInt(tfQuantidade.getText()));

        try{
            if(artesanatoOriginal != null){
                artesanatoRepository.editar(artesanatoOriginal.getId(),artesanato);
            }else{
                artesanatoRepository.adicionar(artesanato);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Artesanato alterado com sucesso!!");
                alert.showAndWait();
            }
        }catch (SQLException e){
            e.printStackTrace();
            mostraMensagem(Alert.AlertType.ERROR,e.getMessage());
        }

        Main.voltaPrincipal();

    }

    @FXML
    void deletarArtesanato() throws SQLException{
        artesanatoRepository.excluir(artesanatoOriginal.getId());
    }

    @FXML
    void voltar() {
        Main.voltaPrincipal();
    }

}

