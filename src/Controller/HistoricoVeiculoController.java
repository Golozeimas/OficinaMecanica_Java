package Controller;

import DB.OrdemServico;
import Model.OrdemDeServico;
import Model.Veiculo;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistoricoVeiculoController {

    @FXML
    private TableColumn<OrdemDeServico, String> colDataAbertura;

    @FXML
    private TableColumn<OrdemDeServico, String> colDataFinalizacao;

    @FXML
    private TableColumn<OrdemDeServico, String> colDescricao;

    @FXML
    private TableColumn<OrdemDeServico, String> colIdOrdem;

    @FXML
    private TableColumn<OrdemDeServico, String> colStatus;

    @FXML
    private TableColumn<OrdemDeServico, String> colValorTotal;

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
    private TableView<OrdemDeServico> tabelaOrdens;

    Alertas alertas = new Alertas();

    private Veiculo veiculoSelecionado;

    public void carregarDadosVeiculo(Veiculo veiculo) {
        this.veiculoSelecionado = veiculo;

        lblPlaca.setText(veiculo.getPlaca());
        lblModelo.setText(veiculo.getModelo());
        lblAno.setText(veiculo.getAno());
        lblProprietario.setText(veiculo.getNomeCliente());

        colIdOrdem.setCellValueFactory(data -> data.getValue().idOrdemProperty());
        colDataAbertura.setCellValueFactory(data -> data.getValue().dataAberturaProperty());
        colDescricao.setCellValueFactory(data -> data.getValue().descricaoProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        colValorTotal.setCellValueFactory(data -> data.getValue().valorTotalProperty());
        colDataFinalizacao.setCellValueFactory(data -> data.getValue().dataFinalizacaoProperty());



    }
        @FXML
    void excluirOrdem(ActionEvent event) {
        OrdemDeServico ordemSelecionada = tabelaOrdens.getSelectionModel().getSelectedItem();
        if (ordemSelecionada == null){
            alertas.mostrarErro("Não foi selecionada nenhuma ordem");
            return;
        }

        boolean sucesso = OrdemServico.deletarOrdem(ordemSelecionada.getIdOrdem());

        if (sucesso){
            alertas.mostrarConfirmacao("Ordem apagada com sucesso!");
        }else{
            alertas.mostrarErro("Ordem não foi apagada, tentar novamente!");
        }

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
