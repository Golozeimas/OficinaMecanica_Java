package Model;

public class Pecas {
    private int idPeca; 
    private int qtdEstoque;
    private double preco; 
    private String nome;
    private String codigo;
    private String fornecedor;
    
    public Pecas(int idPeca, int qtdEstoque, String nome, String codigo, String fornecedor) {
        this.idPeca = idPeca; 
        this.qtdEstoque = qtdEstoque;
        this.nome = nome;
        this.codigo = codigo;
        this.fornecedor = fornecedor;
    }
    public void addEstoque(int qtd){ 
        if (qtd > 0) {
            this.qtdEstoque += qtd;   
        }else{
            System.out.println("Insira uma quantidade válida");
        }
    }
    public void atualizarPreco(double preco){
        if (preco >= 0) {
            this.preco = preco;   
        }else{
            System.out.println("Insira um preço válido");
        }
    }
    public void rmEstoque(int qtd){ 
        if (qtd > 0) {
         this.qtdEstoque -= qtd;   
        }else{
            System.out.println("Insira uma quantidade válida");
        }
    }
    public boolean verificarDisponibilidade(){return qtdEstoque > 0 ? true : false;}
    
    public int getIdPeca() {
        return idPeca;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public double getPreco() {
        return preco;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    


}


