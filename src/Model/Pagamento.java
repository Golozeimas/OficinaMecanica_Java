package Model;
import java.util.Date;

public class Pagamento {
    private int idPagamento;
    private int idOrdemDeServico;
    private double valor;
    private String status;
    private String formaDePagamento;
    private Date dataDoAno;

    public void registrarPagamento(){throw new UnsupportedOperationException("TODO");}
    public void estornarPagamento(){throw new UnsupportedOperationException("TODO");}
    public void consultarStatus(){throw new UnsupportedOperationException("TODO");}
    
    public Pagamento(int idPagamento, Date dataDoAno, double valor, String status, String formaDePagamento,
            int idOrdemDeServico) {
        this.idPagamento = idPagamento;
        this.dataDoAno = dataDoAno;
        this.valor = valor;
        this.status = status;
        this.formaDePagamento = formaDePagamento;
        this.idOrdemDeServico = idOrdemDeServico;
    }
    public int getIdPagamento() {
        return idPagamento;
    }
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }
    public Date getDataDoAno() {
        return dataDoAno;
    }
    public void setDataDoAno(Date dataDoAno) {
        this.dataDoAno = dataDoAno;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFormaDePagamento() {
        return formaDePagamento;
    }
    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
    public int getIdOrdemDeServico() {
        return idOrdemDeServico;
    }
    public void setIdOrdemDeServico(int idOrdemDeServico) {
        this.idOrdemDeServico = idOrdemDeServico;
    }

    
    
}
