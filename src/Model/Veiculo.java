package Model;
import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private int idVeiculo;
    private int clienteId;
    private String modelo;
    private String ano;
    private String placa;
    private List<String> historicoManutencoes;    

    //Cria um veiculo novo a cada instacia de objeto
    public Veiculo(int idVeiculo, int clienteId, String modelo, String ano, String placa) {
        this.idVeiculo = idVeiculo;
        this.clienteId = clienteId;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.historicoManutencoes = new ArrayList<>();
    }

    /// Para atualizar as informações do veiculo utilize os setters
    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }


    //Retorna o historico de manutenções. UML -> consultarHistoricoDoVeiculo()
    public List<String> getHistoricoManutencoes() {
        return historicoManutencoes;
    }

    //Atualiza o historico de manutencoes
    public void addHistoricoManutencoes(String manutencao) {
        this.historicoManutencoes.add(manutencao);
    }

    


}
