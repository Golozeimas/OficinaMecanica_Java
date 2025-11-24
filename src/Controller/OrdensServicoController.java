package Controller;

import DB.OrdemServicoDAO;
import Model.MudarTela;
import Model.OrdemDeServico;
import Templates.Alertas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrdensServicoController implements Initializable {

    @FXML
    private TableColumn<OrdemDeServico, String> colCliente;

    @FXML
    private TableColumn<OrdemDeServico, String> colDataAbertura;

    @FXML
    private TableColumn<OrdemDeServico, String> colDescricao;

    @FXML
    private TableColumn<OrdemDeServico, String> colId;

    @FXML
    private TableColumn<OrdemDeServico, String> colStatus;

    @FXML
    private TableColumn<OrdemDeServico, String> colValorTotal;

    @FXML
    private TableColumn<OrdemDeServico, String> colVeiculo;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<OrdemDeServico> tabelaOrdens;

    Alertas alertas = new Alertas();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colId.setCellValueFactory(data -> data.getValue().idOrdemProperty());
        colVeiculo.setCellValueFactory(data -> data.getValue().veiculoPlacaProperty());
        colCliente.setCellValueFactory(data -> data.getValue().clienteNomeProperty());
        colDescricao.setCellValueFactory(data -> data.getValue().descricaoProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        colDataAbertura.setCellValueFactory(data -> data.getValue().dataAberturaProperty());
        colValorTotal.setCellValueFactory(data -> data.getValue().valorTotalProperty());


        carregarOrdens();
    }

    private void carregarOrdens() {
        tabelaOrdens.setItems(OrdemServicoDAO.listarTodasOrdens());
        atualizarTotal();
    }

    private void atualizarTotal() {
        lblTotal.setText(String.valueOf(tabelaOrdens.getItems().size()));
    }
    @FXML
    void excluirOrdem(ActionEvent event) {
        OrdemDeServico ordemSelecionada = tabelaOrdens.getSelectionModel().getSelectedItem();

        if (ordemSelecionada == null) {
            alertas.mostrarErro("Selecione uma ordem de serviço para excluir!");
            return;
        }

        boolean sucesso = OrdemServicoDAO.deletarOrdem(ordemSelecionada.getIdOrdem());

            if (sucesso) {
                alertas.mostrarConfirmacao("Ordem de serviço excluída com sucesso!");
                carregarOrdens();
            } else {
                alertas.mostrarErro("Erro ao excluir ordem de serviço!");
            }
    }

    @FXML
    void irParaNova(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/CriarOrdemServico.fxml");
    }

    @FXML
    void irParaVeiculos(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/Veiculos.fxml");
    }

    @FXML
    void verDetalhes(ActionEvent event) throws IOException {
        OrdemDeServico ordemSelecionada = tabelaOrdens.getSelectionModel().getSelectedItem();

        if (ordemSelecionada == null) {
            alertas.mostrarErro("Selecione uma ordem de serviço para ver detalhes!");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DetalhesOrdemServico.fxml"));
        Parent root = loader.load();

        DetalhesOrdemServicoController controller = loader.getController();
        controller.carregarDadosOrdem(ordemSelecionada);


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.show();

        // Redirecionar para tela de detalhes/edição
        alertas.mostrarConfirmacao("Detalhes da ordem #" + ordemSelecionada.getIdOrdem() + " serão exibidos!");
    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException{
        MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }

}
