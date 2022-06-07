package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usuario;
import utils.ResourceManager;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
    private ObservableList<Usuario> users = FXCollections.observableArrayList();

    private Usuario usuario;

    private File file = new File("src/main/resources/Arquivos/listaUsuarios.pjt");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(file.exists()){
            users = carregarUsuarios();
        }else{
            salvarUsuarios();
        }
    }

    @FXML
    public void logar(ActionEvent event) throws IOException {
        if(txtUser.getText().isEmpty() && txtPass.getText().isEmpty()){
            msgErroLogin.setText("Campos de login e senha obrigatórios");
            msgErroLogin.setVisible(true);
        } else if (txtUser.getText().equals("admin") && txtPass.getText().equals("admin")) {
            usuario = new Usuario();
            usuario.setCpf("777777777-77");
            usuario.setNome("ADMINISTRADOR ALTO NÍVEL");
            FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaAdmin.fxml"));

            Parent root = fxmloader.load();
            Scene novaCena = new Scene(root);
            //Da acesso ao controller do ExibirAluno;
            TelaAdminController controller = fxmloader.getController();
            controller.initData(usuario);
            //Pega a informação do Stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(novaCena);
            window.show();
            //Change to the next screen;
        }else if (checkCredencials()){
            Usuario user = getUsuario();

            if(user.getAcesso() == 'A'){
                FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaAdmin.fxml"));
                Parent root = fxmloader.load();
                Scene novaCena = new Scene(root);
                //Da acesso ao controller do ExibirAluno;
                TelaAdminController controller = fxmloader.getController();
                controller.initData(usuario);
                //Pega a informação do Stage
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(novaCena);
                window.show();
            } else if (user.getAcesso() == 'E') {
                FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaAluno.fxml"));
                Parent root = fxmloader.load();
                Scene novaCena = new Scene(root);
                //Da acesso ao controller do Exibir Aluno;
                TelaAlunoController controller = fxmloader.getController();
                controller.initData(usuario);
                //Pega a informação do Stage
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(novaCena);
                window.show();
            } else if (user.getAcesso() == 'P') {
                FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaProfessor.fxml"));
                Parent root = fxmloader.load();
                Scene novaCena = new Scene(root);
                //Da acesso ao controller do Tela Professor;
                TelaProfessorController controller = fxmloader.getController();
                controller.initData(usuario);
                //Pega a informação do Stage
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(novaCena);
                window.show();
            }
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

    private boolean checkCredencials(){
        for(Usuario usuario : users){
            if(usuario.getCpf().compareTo(txtUser.getText())==0 && usuario.getSenha().compareTo(txtPass.getText())==0){
                return true;
            }
        }
        return false;
    }

    private Usuario getUsuario(){
        for(Usuario user : users){
            if(user.getCpf().compareTo(txtUser.getText()) == 0){
                usuario = user;
                return user;
            }
        }
        return null;
    }
}