package Controller;

import DB.PecaDAO;
import Model.Peca;
import Templates.Alertas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AjustarEstoqueController implements Initializable {

    @FXML
    private ComboBox<String> comboOperacao;

    @FXML
    private Label lblEstoqueAtual;

    @FXML
    private Label lblNomePeca;

    @FXML
    private Label lblNovoEstoque;

    @FXML
    private TextField txtQuantidade;

    private Peca pecaAtual;
    private int estoqueAtual;
    private Alertas alertas = new Alertas();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboOperacao.setItems(FXCollections.observableArrayList(
                "Entrada (Adicionar ao estoque)",
                "Saída (Remover do estoque)"
        ));

        txtQuantidade.textProperty().addListener((obs, old, novo) -> calcularNovoEstoque());
        comboOperacao.valueProperty().addListener((obs, old, novo) -> calcularNovoEstoque());
    }

    public void carregarPeca(Peca peca) {
        this.pecaAtual = peca;
        lblNomePeca.setText(peca.getNomePeca());
        this.estoqueAtual = Integer.parseInt(peca.getQuantidadeEstoque());
        lblEstoqueAtual.setText(String.valueOf(estoqueAtual));
        lblNovoEstoque.setText("Novo estoque: " + estoqueAtual);
    }

    private void calcularNovoEstoque() {
        try {
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            String operacao = comboOperacao.getValue();
            int novoEstoque = estoqueAtual;

            if (operacao != null) {
                if (operacao.startsWith("Entrada")) {
                    novoEstoque = estoqueAtual + quantidade;
                } else if (operacao.startsWith("Saída")) {
                    novoEstoque = estoqueAtual - quantidade;
                }
            }

            lblNovoEstoque.setText("Novo estoque: " + novoEstoque);

            if (novoEstoque < 0) {
                lblNovoEstoque.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            } else {
                lblNovoEstoque.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            }

        } catch (NumberFormatException e) {
            lblNovoEstoque.setText("Novo estoque: " + estoqueAtual);
        }
    }

    @FXML
    void confirmar(ActionEvent event) {
        if (comboOperacao.getValue() == null || txtQuantidade.getText().isEmpty()) {
            alertas.mostrarErro("Preencha todos os campos!");
            return;
        }

        try {
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            String operacao = comboOperacao.getValue();

            if (operacao.startsWith("Saída")) {
                quantidade = -quantidade;
            }

            // Verificar se não ficará negativo
            if (estoqueAtual + quantidade < 0) {
                alertas.mostrarErro("Estoque não pode ficar negativo!");
                return;
            }

            boolean sucesso = PecaDAO.ajustarEstoque(pecaAtual.getIdPeca(), quantidade);

            if (sucesso) {
                alertas.mostrarConfirmacao("Estoque ajustado com sucesso!");
                fecharJanela();
            } else {
                alertas.mostrarErro("Erro ao ajustar estoque!");
            }

        } catch (NumberFormatException e) {
            alertas.mostrarErro("Quantidade inválida!");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) lblNomePeca.getScene().getWindow();
        stage.close();
    }

}
