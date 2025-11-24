package Controller;

import DB.RelatorioDAO;
import Model.MudarTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class RelatorioController implements Initializable {

    @FXML private Label lblFaturamento;
    @FXML private Label lblOrdensFinalizadas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarDados();
    }

    private void carregarDados() {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        lblFaturamento.setText(df.format(RelatorioDAO.obterFaturamentoTotal()));
        lblOrdensFinalizadas.setText(String.valueOf(RelatorioDAO.obterTotalOrdensFinalizadas()));
    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }
}