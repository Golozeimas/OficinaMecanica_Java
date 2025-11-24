package Model;

import javafx.beans.property.SimpleStringProperty;

public class Pagamento {
    private SimpleStringProperty idPagamento;
    private SimpleStringProperty idOrdem;
    private SimpleStringProperty valorTotal;
    private SimpleStringProperty formaPagamento;
    private SimpleStringProperty statusPagamento;
    private SimpleStringProperty dataPagamento;

    public Pagamento(String idPagamento, String idOrdem, String valorTotal, String formaPagamento,
                     String statusPagamento, String dataPagamento) {
        this.idPagamento = new SimpleStringProperty(idPagamento);
        this.idOrdem = new SimpleStringProperty(idOrdem);
        this.valorTotal = new SimpleStringProperty(valorTotal);
        this.formaPagamento = new SimpleStringProperty(formaPagamento);
        this.statusPagamento = new SimpleStringProperty(statusPagamento);
        this.dataPagamento = new SimpleStringProperty(dataPagamento);
    }


    public SimpleStringProperty idPagamentoProperty() { return idPagamento; }
    public SimpleStringProperty idOrdemProperty() { return idOrdem; }
    public SimpleStringProperty valorTotalProperty() { return valorTotal; }
    public SimpleStringProperty formaPagamentoProperty() { return formaPagamento; }
    public SimpleStringProperty statusPagamentoProperty() { return statusPagamento; }
    public SimpleStringProperty dataPagamentoProperty() { return dataPagamento; }


    public String getIdPagamento() { return idPagamento.get(); }
    public String getIdOrdem() { return idOrdem.get(); }
    public String getValorTotal() { return valorTotal.get(); }
    public String getFormaPagamento() { return formaPagamento.get(); }
    public String getStatusPagamento() { return statusPagamento.get(); }
    public String getDataPagamento() { return dataPagamento.get(); }
}