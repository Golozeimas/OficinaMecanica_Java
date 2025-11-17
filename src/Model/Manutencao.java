package Model;
import java.util.Date;

public class Manutencao {
    private int idManutencao;
    private int idOrdemDeServico;
    private int idVeiculo;
    private String tipo;
    private double custoTotal;
    private Date dataDoAno;

    public void registarManutencao(){throw new UnsupportedOperationException("TODO");}
    //EU ACHO QUE O METODO consultarHistoricoDeManutencoes() da UML não é necessario aqui, visto que é para ser uma responsabilidade da classe veiculo

    public Manutencao(int idManutencao, int idOrdemDeServico, int idVeiculo, String tipo, double custoTotal,
            Date dataDoAno) {
        this.idManutencao = idManutencao;
        this.idOrdemDeServico = idOrdemDeServico;
        this.idVeiculo = idVeiculo;
        this.tipo = tipo;
        this.custoTotal = custoTotal;
        this.dataDoAno = dataDoAno;
    }

    public int getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }

    public int getIdOrdemDeServico() {
        return idOrdemDeServico;
    }

    public void setIdOrdemDeServico(int idOrdemDeServico) {
        this.idOrdemDeServico = idOrdemDeServico;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Date getDataDoAno() {
        return dataDoAno;
    }

    public void setDataDoAno(Date dataDoAno) {
        this.dataDoAno = dataDoAno;
    }

    
    
}
