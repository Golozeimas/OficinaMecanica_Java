package Controller;

import DB.OrdemServicoDAO;
import DB.PagamentoDAO;
import Model.ItemPeca;
import Model.MudarTela;
import Model.OrdemDeServico;
import Model.Pagamento;
import Templates.Alertas;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;

public class DetalhesOrdemServicoController {

    @FXML private Label lblTitulo;
    @FXML private Label lblVeiculo;
    @FXML private Label lblCliente;
    @FXML private Label lblDataAbertura;
    @FXML private Label lblDataFinalizacao;
    @FXML private Label lblDescricao;
    @FXML private ComboBox<String> comboStatus;
    @FXML private TableView<ItemPeca> tabelaPecas;
    @FXML private TableColumn<ItemPeca, String> colPecaNome;
    @FXML private TableColumn<ItemPeca, String> colQuantidade;
    @FXML private TableColumn<ItemPeca, String> colPrecoUnit;
    @FXML private TableColumn<ItemPeca, String> colTotal;
    @FXML private Label lblMaoObra;
    @FXML private Label lblTotalPecas;
    @FXML private Label lblValorTotal;
    @FXML private Label lblStatusPagamento;
    @FXML private VBox boxPagamento;

    private OrdemDeServico ordemAtual;
    private Alertas alertas = new Alertas();
    private String telaOrigem;
    private DecimalFormat df = new DecimalFormat("R$ #,##0.00");

    public void carregarDadosOrdem(OrdemDeServico ordem, String origem) {
        this.ordemAtual = ordem;
        this.telaOrigem = origem;

        lblTitulo.setText("Detalhes da Ordem de Serviço #" + ordem.getIdOrdem());

        lblVeiculo.setText(ordem.getVeiculoPlaca());
        lblCliente.setText(ordem.getClienteNome());
        lblDataAbertura.setText(ordem.getDataAbertura());
        lblDataFinalizacao.setText(ordem.getDataFinalizacao());
        lblDescricao.setText(ordem.getDescricao());

        comboStatus.setItems(FXCollections.observableArrayList(
                "Em Serviço", "Aguardando Peças", "Pronto para Entrega", "Finalizado"
        ));
        comboStatus.setValue(ordem.getStatus());

        colPecaNome.setCellValueFactory(data -> data.getValue().nomePecaProperty());
        colQuantidade.setCellValueFactory(data -> data.getValue().quantidadeProperty());
        colPrecoUnit.setCellValueFactory(data -> data.getValue().precoUnitarioProperty());
        colTotal.setCellValueFactory(data -> data.getValue().totalProperty());

        tabelaPecas.setItems(OrdemServicoDAO.listarPecasDaOrdem(ordem.getIdOrdem()));

        calcularValores();
        verificarPagamento();
    }

    private void verificarPagamento() {
        Pagamento pagamento = PagamentoDAO.buscarPagamentoPorOrdem(ordemAtual.getIdOrdem());

        if (pagamento != null && pagamento.getStatusPagamento().equals("Pago")) {
            lblStatusPagamento.setText("✓ Pago - " + pagamento.getFormaPagamento() + " em " + pagamento.getDataPagamento());
            boxPagamento.setStyle("-fx-background-color: #d4edda; -fx-padding: 15; -fx-background-radius: 5;");
        } else {
            lblStatusPagamento.setText("⚠ Pendente");
            boxPagamento.setStyle("-fx-background-color: #fff3cd; -fx-padding: 15; -fx-background-radius: 5;");
        }
    }

    private void calcularValores() {
        try {
            String maoObraStr = ordemAtual.getValorMaoObra().replace("R$", "").replace(".", "").replace(",", ".").trim();
            double maoObra = Double.parseDouble(maoObraStr);
            lblMaoObra.setText(ordemAtual.getValorMaoObra());

            double totalPecas = 0;
            for (ItemPeca item : tabelaPecas.getItems()) {
                String totalStr = item.getTotal().replace("R$", "").replace(".", "").replace(",", ".").trim();
                totalPecas += Double.parseDouble(totalStr);
            }

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");
            lblTotalPecas.setText(df.format(totalPecas));
            lblValorTotal.setText(df.format(maoObra + totalPecas));

        } catch (Exception e) {
            System.out.println("Erro ao calcular valores: " + e.getMessage());
        }
    }

    @FXML
    void atualizarStatus(ActionEvent event) {
        String novoStatus = comboStatus.getValue();

        if (novoStatus == null) {
            alertas.mostrarErro("Selecione um status!");
            return;
        }


            boolean sucesso = OrdemServicoDAO.atualizarStatus(ordemAtual.getIdOrdem(), novoStatus);

            if (sucesso) {
                alertas.mostrarConfirmacao("Status atualizado com sucesso!");
                ordemAtual.setStatus(novoStatus);

                // Se finalizou, atualizar data
                if (novoStatus.equals("Finalizado")) {
                    lblDataFinalizacao.setText(java.time.LocalDateTime.now().toString());
                }
            } else {
                alertas.mostrarErro("Erro ao atualizar status!");
            }

    }

    @FXML
    void registrarPagamento(ActionEvent event) {

        // Verificar se já foi pago
        if (PagamentoDAO.ordemJaPaga(ordemAtual.getIdOrdem())) {
            alertas.mostrarConfirmacao("Esta ordem já foi paga!");
            return;
        }

        // Dialog para escolher forma de pagamento
        ChoiceDialog<String> dialog = new ChoiceDialog<>("PIX",
                "Dinheiro", "Cartão Débito", "Cartão Crédito", "PIX", "Boleto");
        dialog.setTitle("Registrar Pagamento");
        dialog.setHeaderText("Ordem de Serviço #" + ordemAtual.getIdOrdem());
        dialog.setContentText("Forma de pagamento:");

        dialog.showAndWait().ifPresent(formaPagamento -> {
            try {
                // Extrair valor total
                String valorStr = lblValorTotal.getText()
                        .replace("R$", "")
                        .replace(" ", "")
                        .replace(".", "")
                        .replace(",", ".")
                        .trim();
                double valor = Double.parseDouble(valorStr);

                boolean sucesso = PagamentoDAO.registrarPagamento(
                        ordemAtual.getIdOrdem(),
                        valor,
                        formaPagamento
                );

                if (sucesso) {
                    alertas.mostrarConfirmacao("Pagamento registrado com sucesso!");
                    verificarPagamento();
                } else {
                    alertas.mostrarErro("Erro ao registrar pagamento!");
                }

            } catch (Exception e) {
                alertas.mostrarErro("Erro ao processar pagamento: " + e.getMessage());
            }
        });
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        if (telaOrigem != null) {
            MudarTela.trocarJanela(event, telaOrigem);
        } else {
            MudarTela.trocarJanela(event, "/View/PainelOrdensServico.fxml");
        }
    }
}