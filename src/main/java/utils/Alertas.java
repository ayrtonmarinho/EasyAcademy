package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alertas {

    public static boolean alertaConfirmar(String titulo, String mensagem, String cabecalho){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        Optional<ButtonType> resposta = alerta.showAndWait();
        if(resposta.get() == ButtonType.OK){
            return true;
        }
        else{
            return false;
        }
    }

    public static void alertaErro(String titulo, String mensagem, String cabecalho){
        Alert erro = new Alert(Alert.AlertType.ERROR);
        erro.setTitle(titulo);
        erro.setHeaderText(cabecalho);
        erro.setContentText(mensagem);
        erro.showAndWait();
    }

    public static  void alertaInformation(String titulo, String mensagem, String cabecalho){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(titulo);
        info.setHeaderText(cabecalho);
        info.setContentText(mensagem);
        info.showAndWait();
    }
}
