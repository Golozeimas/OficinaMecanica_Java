package Controller;

import DB.PecaDAO;
import Model.MudarTela;
import Model.Peca;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class PecasController implements Initializable {

    @FXML
    private TableColumn<Peca, String> colEstoque;

    @FXML
    private TableColumn<Peca, String> colId;

    @FXML
    private TableColumn<Peca, String> colNome;

    @FXML
    private TableColumn<Peca, String> colPreco;

    @FXML
    private Label lblAlertas;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblValorEstoque;

    @FXML
    private TableView<Peca> tabelaPecas;

    @FXML
    private TextField txtBusca;

    private Alertas alertas = new Alertas();
    private DecimalFormat df = new DecimalFormat("R$ #,##0.00");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(data -> data.getValue().idPecaProperty());
        colNome.setCellValueFactory(data -> data.getValue().nomePecaProperty());
        colPreco.setCellValueFactory(data -> data.getValue().precoUnitarioProperty());
        colEstoque.setCellValueFactory(data -> data.getValue().quantidadeEstoqueProperty());

        carregarPecas();
        verificarEstoqueBaixo();
    }

    private void carregarPecas() {
        tabelaPecas.setItems(PecaDAO.listarPecas());
        atualizarRodape();
    }

    private void atualizarRodape() {
        lblTotal.setText(String.valueOf(tabelaPecas.getItems().size()));
        lblValorEstoque.setText(df.format(PecaDAO.calcularValorTotalEstoque()));
    }
    private void verificarEstoqueBaixo() {
        var estoqueBaixo = PecaDAO.listarEstoqueBaixo();
        if (estoqueBaixo.isEmpty()) {
            lblAlertas.setText("Nenhum alerta de estoque");
        } else {
            lblAlertas.setText(estoqueBaixo.size() + " peça(s) com estoque baixo (menos de 10 unidades)");
        }
    }

    @FXML
    void ajustarEstoque(ActionEvent event) {
        Peca pecaSelecionada = tabelaPecas.getSelectionModel().getSelectedItem();
        if (pecaSelecionada == null) {
            alertas.mostrarErro("Selecione uma peça para ajustar o estoque!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AjustarEstoque.fxml"));
            Parent root = loader.load();

            AjustarEstoqueController controller = loader.getController();
            controller.carregarPeca(pecaSelecionada);

            Stage stage = new Stage();
            stage.setTitle("Ajustar Estoque");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            carregarPecas();
            verificarEstoqueBaixo();

        } catch (IOException e) {
            alertas.mostrarErro("Erro ao abrir tela de ajuste!");
        }
    }

    @FXML
    void excluirPeca(ActionEvent event) {
        Peca pecaSelecionada = tabelaPecas.getSelectionModel().getSelectedItem();
        if (pecaSelecionada == null) {
            alertas.mostrarErro("Selecione uma peça para excluir!");
            return;
        }

        boolean sucesso = PecaDAO.deletarPeca(pecaSelecionada.getIdPeca());
        if (sucesso) {
            alertas.mostrarErro("Peça excluída com sucesso!");
            carregarPecas();
            verificarEstoqueBaixo();
        } else {
            alertas.mostrarErro("Erro ao excluir peça!");
        }
    }

    @FXML
    void irParaAdicionar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/AdicionarPeca.fxml");
    }
    @FXML
    void irParaEditar(ActionEvent event) {

    }

    @FXML
    void irParaOrdens(ActionEvent event) throws IOException {
    MudarTela.trocarJanela(event, "/View/PainelOrdensServicos.fxml");
    }

    @FXML
    void irParaVeiculos(ActionEvent event) throws IOException {
    MudarTela.trocarJanela(event, "/View/Veiculos.fxml");
    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException {
    MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }

}
