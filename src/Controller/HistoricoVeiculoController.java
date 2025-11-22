package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistoricoVeiculoController {

    @FXML
    private TableColumn<?, ?> colDataAbertura;

    @FXML
    private TableColumn<?, ?> colDataFinalizacao;

    @FXML
    private TableColumn<?, ?> colDescricao;

    @FXML
    private TableColumn<?, ?> colIdOrdem;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colValorTotal;

    @FXML
    private Label lblAno;

    @FXML
    private Label lblModelo;

    @FXML
    private Label lblPlaca;

    @FXML
    private Label lblProprietario;

    @FXML
    private Label lblTotalServicos;

    @FXML
    private Label lblValorTotal;

    @FXML
    private TableView<?> tabelaOrdens;

    @FXML
    void excluirOrdem(ActionEvent event) {

    }

    @FXML
    void novaOrdemServico(ActionEvent event) {

    }

    @FXML
    void verDetalhes(ActionEvent event) {

    }

    @FXML
    void voltarParaVeiculos(ActionEvent event) {

    }

}
