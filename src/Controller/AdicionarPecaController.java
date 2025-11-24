package Controller;

import DB.PecaDAO;
import Model.MudarTela;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdicionarPecaController {

    @FXML
    private Label lblMensagem;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQuantidade;

    Alertas alertas = new Alertas();

    @FXML
    void salvarPeca(ActionEvent event) throws IOException {
        String nome = txtNome.getText().trim();
        String precoStr = txtPreco.getText().trim();
        String qtdStr = txtQuantidade.getText().trim();

        if (nome.isEmpty() || precoStr.isEmpty() || qtdStr.isEmpty()) {
            lblMensagem.setText("Preencha todos os campos!");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr.replace(",", "."));
            int quantidade = Integer.parseInt(qtdStr);

            if (preco <= 0 || quantidade < 0) {
                lblMensagem.setText("Valores inválidos!");
                return;
            }

            boolean sucesso = PecaDAO.adicionarPeca(nome, preco, quantidade);

            if (sucesso) {
                alertas.mostrarConfirmacao("Peça cadastrada com sucesso!");
                MudarTela.trocarJanela(event, "/View/PainelPecas.fxml");
            } else {
                lblMensagem.setText("Erro ao cadastrar peça!");
            }

        } catch (NumberFormatException e) {
            lblMensagem.setText("Preço ou quantidade inválidos!");
        }
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelPecas.fxml");
    }

}
