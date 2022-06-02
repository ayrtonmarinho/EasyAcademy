package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {
    @FXML
    private Label msgErroLogin;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private ImageView imgLogotipo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logar(){

        if(msgErroLogin.isVisible()){
            msgErroLogin.setVisible(false);
        }else{
            msgErroLogin.setVisible(true);
            msgErroLogin.setText("Sua senha esta errada!");
        }
    }
}