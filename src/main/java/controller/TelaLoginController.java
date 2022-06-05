package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Aluno;
import model.Usuario;
import utils.ResourceManager;


import java.net.URL;
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

    @FXML
    private ObservableList<Usuario> carregarUsuarios() {
        try {
            List<Usuario> list = (List<Usuario>) ResourceManager.load("ListaTurmas");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();

    }

    /*
    @FXML
    private void salvarListaTurmas() {
        ArrayList<Turma> tempList = new ArrayList<>(listTurma);

        try {
            ResourceManager.save(tempList, "ListaTurmas");
        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     */
}