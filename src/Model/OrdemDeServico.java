package Model;

public class OrdemDeServico {
    private int idOrdemDeServico;
    private int idAdministrador;
    private int idVeiculo;
    private String descricao;
    private String statusDoServico;
    private double valorMaoDeObra;
    private double valorTotal;

    public boolean criarOrdemDeServico(){throw new UnsupportedOperationException("TODO");}
    public void atualizarStatus(){throw new UnsupportedOperationException("TODO");}
    public void calcularTotal(){throw new UnsupportedOperationException("TODO");}
    public boolean finalizarOrdemDeServico(){throw new UnsupportedOperationException("TODO");}
    
    public OrdemDeServico(int idOrdemDeServico, int idAdministrador, int idVeiculo, String descricao,
            String statusDoServico, double valorMaoDeObra, double valorTotal) {
        this.idOrdemDeServico = idOrdemDeServico;
        this.idAdministrador = idAdministrador;
        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.statusDoServico = statusDoServico;
        this.valorMaoDeObra = valorMaoDeObra;
        this.valorTotal = valorTotal;
    }
    public int getIdOrdemDeServico() {
        return idOrdemDeServico;
    }
    public void setIdOrdemDeServico(int idOrdemDeServico) {
        this.idOrdemDeServico = idOrdemDeServico;
    }
    public int getIdAdministrador() {
        return idAdministrador;
    }
    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getStatusDoServico() {
        return statusDoServico;
    }
    public void setStatusDoServico(String statusDoServico) {
        this.statusDoServico = statusDoServico;
    }
    public double getValorMaoDeObra() {
        return valorMaoDeObra;
    }
    public void setValorMaoDeObra(double valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}

