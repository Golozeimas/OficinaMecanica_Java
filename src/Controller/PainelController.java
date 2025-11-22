package Controller;


import DB.ClienteDAO;
import Model.Cliente;
import Model.MudarTela;
import Templates.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PainelController implements Initializable {

    @FXML
    private TableColumn<Cliente, String> colCpf;

    @FXML
    private TableColumn<Cliente, String> colId;

    @FXML
    private TableColumn<Cliente, String> colNome;

    @FXML
    private TableColumn<Cliente, String> colTelefone;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<Cliente> tabelaDados;

    @FXML
    private TextField txtBusca;

    Alertas alertas = new Alertas();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colNome.setCellValueFactory(data-> data.getValue().nomeProperty());
        colCpf.setCellValueFactory( data->data.getValue().cpfProperty());
        colTelefone.setCellValueFactory( data->data.getValue().telefoneProperty());

        carregarClientes();
    }

    private void carregarClientes(){
        tabelaDados.setItems(ClienteDAO.listarClientes());
    }

    @FXML
    void irParaAdicionar(ActionEvent event) throws IOException {
        MudarTela.trocarJanela(event, "/view/AdicionarClientes.fxml");
    }

    @FXML
    void excluirCliente(ActionEvent event){
        // pega o item que tu seleciona da tabela, é metodo do JavaFX também
        Cliente clienteSelecionado = tabelaDados.getSelectionModel().getSelectedItem();

        if (clienteSelecionado == null){
            alertas.mostrarErro("Selecione algum cliente");
        }

        boolean clienteExcluido = ClienteDAO.deletarCliente(clienteSelecionado.getId());

        if (clienteExcluido){
            alertas.mostrarConfirmacao("cliente deletado com SUCESSO");
        }else {
            alertas.mostrarErro("Não foi possível deletar o cliente");
        }

    }

}
