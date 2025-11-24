package Controller;

import Model.Peca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjustarEstoqueController {

    @FXML
    private ComboBox<?> comboOperacao;

    @FXML
    private Label lblEstoqueAtual;

    @FXML
    private Label lblNomePeca;

    @FXML
    private Label lblNovoEstoque;

    @FXML
    private TextField txtQuantidade;

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void confirmar(ActionEvent event) {

    }

    public void carregarPeca(Peca pecaSelecionada) {

    }
}
