package Templates;

import javafx.scene.control.Alert;

public class Alertas {

    public void mostrarConfirmacao(String alerta){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Operação realizado com sucesso!" );
        alert.setContentText(alerta);
        alert.showAndWait();
    }

    public void mostrarErro(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no banco de dados");
        alert.setHeaderText("Esse erro ocorreu na execução do banco de dados");
        alert.setContentText("Erro");
        alert.showAndWait();
    }

    public void mostrarErro(String erro){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no email");
        alert.setHeaderText("Erro de digitação, por favor, repita!");
        alert.setContentText(erro);
        alert.showAndWait();
    }

    public void mostrarErroTelefone(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no número de telefone");
        alert.setHeaderText("Erro no telefone, verifique o número");
        alert.setContentText("Erro no telefone");
        alert.showAndWait();
    }
}
