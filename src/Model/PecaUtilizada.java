package Model;

public class PecaUtilizada {
    private int idPecas;
    private int idPecaUtilizada;
    private int idManutencao;
    private int qtdPecasUtilizadas;
    private double precoUnitario;
    private double subTotal;
    private double custoTotal;

    public void registrarUso(){throw new UnsupportedOperationException("TODO");}
    public void calcularSubtotal(){throw new UnsupportedOperationException("TODO");}
    public PecaUtilizada(int idPecas, int idPecaUtilizada, int idManutencao, int qtdPecasUtilizadas,
    
    double precoUnitario, double subTotal, double custoTotal) {
        this.idPecas = idPecas;
        this.idPecaUtilizada = idPecaUtilizada;
        this.idManutencao = idManutencao;
        this.qtdPecasUtilizadas = qtdPecasUtilizadas;
        this.precoUnitario = precoUnitario;
        this.subTotal = subTotal;
        this.custoTotal = custoTotal;
    }
    public int getIdPecas() {
        return idPecas;
    }
    public void setIdPecas(int idPecas) {
        this.idPecas = idPecas;
    }
    public int getIdPecaUtilizada() {
        return idPecaUtilizada;
    }
    public void setIdPecaUtilizada(int idPecaUtilizada) {
        this.idPecaUtilizada = idPecaUtilizada;
    }
    public int getIdManutencao() {
        return idManutencao;
    }
    public void setIdManutencao(int idManutencao) {
        this.idManutencao = idManutencao;
    }
    public int getQtdPecasUtilizadas() {
        return qtdPecasUtilizadas;
    }
    public void setQtdPecasUtilizadas(int qtdPecasUtilizadas) {
        this.qtdPecasUtilizadas = qtdPecasUtilizadas;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public double getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    public double getCustoTotal() {
        return custoTotal;
    }
    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }
    
    
    
}
