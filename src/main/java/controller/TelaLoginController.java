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
import model.Aluno;
import model.Professor;
import model.Usuario;
import utils.ResourceManager;


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

    private ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    private ObservableList<Professor> professores = FXCollections.observableArrayList();

    private Usuario usuario;

    private Aluno aluno;

    private Professor professor;

    private File file = new File("src/main/resources/Arquivos/listaUsuarios.pjt");

    private File fileA = new File("src/main/resources/Arquivos/listaAlunos.pjt");

    private File fileP = new File("src/main/resources/Arquivos/listaProfessores.pjt");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(file.exists()){
            users = carregarUsuarios();
        }
        if(fileA.exists()) {
            alunos = carregarAlunos();
        }
        if(fileP.exists()){
            professores = carregarProfessores();
        }
    }

    @FXML
    public void logar(ActionEvent event) throws IOException {
        if(txtUser.getText().isEmpty() || txtPass.getText().isEmpty()){
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
        }else if (checkCredencials() != 'N'){
            Usuario user = new Usuario();
            Professor prof = new Professor();
            Aluno alun = new Aluno();

            if(checkCredencials() == 'A')
                user = getUsuario();
            else if(checkCredencials() == 'P')
                prof = getProfessor();
            else if(checkCredencials() == 'E')
                alun = getAluno();


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
            } else if (alun.getAcesso() == 'E') {
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
            } else if (prof.getAcesso() == 'P') {
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

    private ObservableList<Aluno> carregarAlunos() {
        try {
            List<Aluno> list = (List<Aluno>) ResourceManager.load("src/main/resources/Arquivos/listaAlunos.pjt");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }


    private ObservableList<Professor> carregarProfessores() {
        try {
            List<Professor> list = (List<Professor>) ResourceManager.load("src/main/resources/Arquivos/listaProfessores.pjt");
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

    private char checkCredencials(){
        for(Usuario usuario : users){
            if(usuario.getCpf().compareTo(txtUser.getText())==0 && usuario.getSenha().compareTo(txtPass.getText())==0){
                return 'A';
            }
        }

        for(Professor professor : professores){
            if(professor.getCpf().compareTo(txtUser.getText())==0 && professor.getSenha().compareTo(txtPass.getText())==0){
                return 'P';
            }
        }

        for(Aluno aluno : alunos){
            if(aluno.getCpf().compareTo(txtUser.getText())==0 && aluno.getSenha().compareTo(txtPass.getText())==0){
                return 'E';
            }
        }
        return 'N';
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

    private Aluno getAluno(){
        for(Aluno alun : alunos){
            if(alun.getCpf().compareTo(txtUser.getText()) == 0){
                aluno = alun;
                return alun;
            }
        }
        return null;
    }

    private Professor getProfessor(){
        for(Professor prof : professores){
            if(prof.getCpf().compareTo(txtUser.getText()) == 0){
                professor = prof;
                return prof;
            }
        }
        return null;
    }
}