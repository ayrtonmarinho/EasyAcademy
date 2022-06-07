package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaAdminController implements Initializable {

    private Usuario user;
    @FXML
    private Label nomeAdmin;

    @FXML
    private Label matriculaAdmin;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void deslogar(ActionEvent event) throws IOException {
        //FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaLogin.fxml"));
        Parent novaCenaParent = FXMLLoader.load(getClass().getClassLoader().getResource("view/TelaLogin.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //novaCena.setFill(javafx.scene.paint.Color.TRANSPARENT);
        window.setScene(novaCena);
        window.show();


    }

    public void goCadastro(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaCadastro.fxml"));
        Parent root = fxmloader.load();
        Scene novaCena = new Scene(root);
        //Da acesso ao controller do ExibirAluno;
        TelaCadastroController controller = fxmloader.getController();
        user.setNome(nomeAdmin.getText());
        user.setCpf(matriculaAdmin.getText());
        controller.initData(user);
        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.show();
    }

    public void initData(Usuario usuario){
        user = usuario;
        nomeAdmin.setText(user.getNome());
        matriculaAdmin.setText(user.getCpf());
    }

    public void fechar(){
        System.exit(0);
    }


}
