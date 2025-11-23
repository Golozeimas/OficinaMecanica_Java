package Model;

import javafx.beans.property.SimpleStringProperty;

public class OrdemDeServico {
    private SimpleStringProperty idOrdem;
    private SimpleStringProperty idVeiculo;
    private SimpleStringProperty descricao;
    private SimpleStringProperty valorMaoObra;
    private SimpleStringProperty status;
    private SimpleStringProperty dataAbertura;
    private SimpleStringProperty dataFinalizacao;
    private SimpleStringProperty valorTotal;
    private SimpleStringProperty veiculoPlaca;
    private SimpleStringProperty clienteNome;

    public OrdemDeServico(String idOrdem, String idVeiculo, String descricao, String valorMaoObra,
                        String status, String dataAbertura, String dataFinalizacao, String valorTotal,
                        String veiculoPlaca, String clienteNome) {
        this.idOrdem = new SimpleStringProperty(idOrdem);
        this.idVeiculo = new SimpleStringProperty(idVeiculo);
        this.descricao = new SimpleStringProperty(descricao);
        this.valorMaoObra = new SimpleStringProperty(valorMaoObra);
        this.status = new SimpleStringProperty(status);
        this.dataAbertura = new SimpleStringProperty(dataAbertura);
        this.dataFinalizacao = new SimpleStringProperty(dataFinalizacao);
        this.valorTotal = new SimpleStringProperty(valorTotal);
        this.veiculoPlaca = new SimpleStringProperty(veiculoPlaca);
        this.clienteNome = new SimpleStringProperty(clienteNome);
    }

    public OrdemDeServico(String idOrdem, String idVeiculo, String descricao, String valorMaoObra,
                        String status, String dataAbertura, String dataFinalizacao, String valorTotal) {
        this(idOrdem, idVeiculo, descricao, valorMaoObra, status, dataAbertura, dataFinalizacao, valorTotal, "", "");
    }

    public SimpleStringProperty idOrdemProperty() {
        return idOrdem;
    }

    public SimpleStringProperty idVeiculoProperty() {
        return idVeiculo;
    }

    public SimpleStringProperty descricaoProperty() {
        return descricao;
    }

    public SimpleStringProperty valorMaoObraProperty() {
        return valorMaoObra;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public SimpleStringProperty dataAberturaProperty() {
        return dataAbertura;
    }

    public SimpleStringProperty dataFinalizacaoProperty() {
        return dataFinalizacao;
    }

    public SimpleStringProperty valorTotalProperty() {
        return valorTotal;
    }

    public SimpleStringProperty veiculoPlacaProperty() {
        return veiculoPlaca;
    }

    public SimpleStringProperty clienteNomeProperty() {
        return clienteNome;
    }

    public String getIdOrdem() {
        return idOrdem.get();
    }

    public void setIdOrdem(String idOrdem) {
        this.idOrdem.set(idOrdem);
    }

    public String getIdVeiculo() {
        return idVeiculo.get();
    }

    public void setIdVeiculo(String idVeiculo) {
        this.idVeiculo.set(idVeiculo);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public String getValorMaoObra() {
        return valorMaoObra.get();
    }

    public void setValorMaoObra(String valorMaoObra) {
        this.valorMaoObra.set(valorMaoObra);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDataAbertura() {
        return dataAbertura.get();
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura.set(dataAbertura);
    }

    public String getDataFinalizacao() {
        return dataFinalizacao.get();
    }

    public void setDataFinalizacao(String dataFinalizacao) {
        this.dataFinalizacao.set(dataFinalizacao);
    }

    public String getValorTotal() {
        return valorTotal.get();
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal.set(valorTotal);
    }

    public String getVeiculoPlaca() {
        return veiculoPlaca.get();
    }

    public void setVeiculoPlaca(String veiculoPlaca) {
        this.veiculoPlaca.set(veiculoPlaca);
    }

    public String getClienteNome() {
        return clienteNome.get();
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome.set(clienteNome);
    }
}