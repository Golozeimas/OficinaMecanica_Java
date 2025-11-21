package Controller;

import Model.MudarTela;
import Model.Validacoes;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdicionarClientesController {

    @FXML
    private Label lblMensagem;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    void limparCampos(ActionEvent event) {

    }

    @FXML
    void salvarCliente(ActionEvent event) {
        Alertas alertas = new Alertas();
        Validacoes validacoes = new Validacoes();
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String telefone = txtTelefone.getText();

        boolean isTelefoneValido = validacoes.validarTelefone(telefone);

        // adicionar validação no cpf também
        if (isTelefoneValido){


        }else{
            alertas.mostrarErroTelefone();
        }
    }

    @FXML
    void voltarParaPainel(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/View/PainelAdministrativo.fxml");
    }

}