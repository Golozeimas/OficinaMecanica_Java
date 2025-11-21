package Model;
import java.util.Date;

public class Relatorio {
    private String datalhes;
    private String tipoDoProblema;
    private Date dataDoAno;

    public void gerarRelatorioCliente(){throw new UnsupportedOperationException("TODO");}
    public void gerarRelatorioFinanceiro(){throw new UnsupportedOperationException("TODO");}
    public void gerarRelatorioEmPDF(){throw new UnsupportedOperationException("TODO");}
    
    public Relatorio(String datalhes, String tipoDoProblema, Date dataDoAno) {
        this.datalhes = datalhes;
        this.tipoDoProblema = tipoDoProblema;
        this.dataDoAno = dataDoAno;
    }
    
    
}
