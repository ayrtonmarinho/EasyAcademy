package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Usuario;
import utils.ResourceManager;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TelaLoginController implements Initializable {
    @FXML
    private Label msgErroLogin;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private ImageView imgLogotipo;

    @FXML
    private ObservableList<Usuario> users;

    private File file = new File("src/main/resources/Arquivos/listaUsuarios.pjt");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(file.exists()){
            System.out.println("Foi");
        }else{
            salvarUsuarios();
        }
    }

    @FXML
    public void logar(){
        if(txtUser.getText().isEmpty() && txtPass.getText().isEmpty()){
            msgErroLogin.setText("Campos de login e senha obrigatórios");
            msgErroLogin.setVisible(true);
        } else if (txtUser.getText().equals("admin") && txtPass.getText().equals("admin")) {
            users = carregarUsuarios();
        }
    }

    private ObservableList<Usuario> carregarUsuarios() {
        try {
            List<Usuario> list = (List<Usuario>) ResourceManager.load("src/main/resources/Arquivos/listaUsuarios.pjt");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    //Salvar
    private void salvarUsuarios() {
        ArrayList<Usuario> tempList = new ArrayList<>(users);

        try {
            ResourceManager.save(tempList, "src/main/resources/Arquivos/listaUsuarios.pjt");
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}