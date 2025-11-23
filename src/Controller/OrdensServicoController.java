package Controller;

import Model.MudarTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class OrdensServicoController {

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colDataAbertura;

    @FXML
    private TableColumn<?, ?> colDescricao;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colValorTotal;

    @FXML
    private TableColumn<?, ?> colVeiculo;

    @FXML
    private ComboBox<?> comboStatus;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<?> tabelaOrdens;

    @FXML
    void excluirOrdem(ActionEvent event) {

    }

    @FXML
    void filtrarPorStatus(ActionEvent event) {

    }

    @FXML
    void irParaNova(ActionEvent event) {

    }

    @FXML
    void irParaVeiculos(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/Veiculos.fxml");
    }

    @FXML
    void verDetalhes(ActionEvent event) {

    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException{
        MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }

}
