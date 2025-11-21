package Controller;


import Model.MudarTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PainelController {

    @FXML
    private Button botaoAdicionar;

    @FXML
    private TableColumn<?, ?> colCpf;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private TableColumn<?, ?> colTelefone;

    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<?> tabelaDados;

    @FXML
    private TextField txtBusca;



}
