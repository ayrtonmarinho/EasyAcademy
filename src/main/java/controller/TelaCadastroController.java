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
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import utils.ResourceManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCadastroController implements Initializable {

    private Usuario user;

    @FXML
    private Label nomeAdmin;

    @FXML
    private Label matriculaAdmin;

    @FXML
    private TextField cad_txtNome;

    @FXML
    private TextField cad_txtCpf;

    @FXML
    private TextField cad_txtMat;

    @FXML
    private TextField cad_txtRua;

    @FXML
    private TextField cad_txtNum;

    @FXML
    private TextField cad_txtCep;

    @FXML
    private TextField cad_txtCidade;

    @FXML
    private TextField cad_txtComplemento;

    @FXML
    private TextField cad_txtTelefone;

    @FXML
    private RadioButton cad_radioProfessor;

    @FXML
    private RadioButton cad_radioAluno;

    @FXML
    private RadioButton cad_radioAdmin;

    @FXML
    private ToggleGroup cad_toggleSelect;

    private ObservableList<Usuario> users;

    private ObservableList<Aluno> alunos;

    private ObservableList<Professor> professores;

    private File file = new File("src/main/resources/Arquivos/listaUsuarios.pjt");

    private File fileA = new File("src/main/resources/Arquivos/listaAlunos.pjt");

    private File fileP = new File("src/main/resources/Arquivos/listaProfessores.pjt");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(file.exists()){
            users = carregarUsuarios();
        }else{
            salvarUsuarios();
        }
    }

    public void cadastrar(){
        Usuario user = new Usuario();
        Endereco endereco = new Endereco();
        Telefone telefone = new Telefone();

        if(cad_radioAdmin.isSelected()){
            user.setAcesso('A');
        } else if (cad_radioAluno.isSelected()) {
            user.setAcesso('E');
        } else if (cad_radioProfessor.isSelected()) {
            user.setAcesso('P');
        }else{

        }

        if(checarCampos()){
            telefone.setDdd(cad_txtTelefone.getText().substring(0, 1));
            telefone.setNumero(cad_txtTelefone.getText().substring(2, 10));
            endereco.setNumero(cad_txtNum.getText());
            endereco.setCep(cad_txtCep.getText());
            endereco.setCidade(cad_txtCidade.getText());
            endereco.setRua(cad_txtRua.getText());
            endereco.setComplemento(cad_txtComplemento.getText());
            user.setEndereco(endereco);
            user.setTelefone(telefone);
            user.setNome(cad_txtNome.getText());
            user.setCpf(cad_txtCpf.getText());

            if(user.getAcesso()=='E'){
                Aluno aluno = (Aluno)user;
                alunos.add(aluno);
                salvarAlunos();
                mensagemSucesso(aluno.getNome(),aluno.getAcesso());
            }else if(user.getAcesso()=='P'){
                Professor professor = (Professor) user;
                professores.add(professor);
                salvarProfessores();
                mensagemSucesso(professor.getNome(), professor.getAcesso());
            }else{
                users.add(user);
                salvarUsuarios();
                mensagemSucesso(user.getNome(),user.getAcesso());
            }
            clearFields();
        }
    }

    public boolean checarCampos(){
        StringBuilder msg = new StringBuilder();

        if(cad_txtNome.getText().isEmpty()){
            msg.append("Nome vazio\n");
        }
        if(cad_txtCpf.getText().isEmpty()){
            msg.append("CPF vazio\n");
        }
        if(cad_txtRua.getText().isEmpty()){
            msg.append("Rua vazio\n");
        }
        if(cad_txtNum.getText().isEmpty()){
            msg.append("Número vazio\n");
        }
        if(cad_txtCep.getText().isEmpty()){
            msg.append("CEP vazio\n");
        }
        if(cad_txtCidade.getText().isEmpty()){
            msg.append("Cidade vazio\n");
        }
        if(cad_txtComplemento.getText().isEmpty()){
            msg.append("Complemento vazio\n");
        }
        if(cad_txtTelefone.getText().isEmpty()){
            msg.append("Telefone vazio\n");
        }

        if(msg.isEmpty()){
            return true;
        }else{
            Alert msgAlert = new Alert(Alert.AlertType.ERROR);
            msgAlert.setTitle("Campos vazios");
            msgAlert.setContentText(msg.toString());
            msgAlert.showAndWait();
            return false;
        }
    }

    //Teste
    public void deslogar(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaLogin.fxml"));

        Parent root = fxmloader.load();
        Scene novaCena = new Scene(root);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        novaCena.setFill(javafx.scene.paint.Color.TRANSPARENT);
        window.setScene(novaCena);
        window.show();
    }

    public void initData(Usuario usuario){
        user = usuario;
        nomeAdmin.setText(user.getNome());
        matriculaAdmin.setText(user.getCpf());
    }

    private void clearFields(){
        //limpar os campos
    }
    public void mensagemSucesso(String nome, char acesso){

        Alert mensagem = new Alert(Alert.AlertType.INFORMATION);
        mensagem.setTitle("Confirmação de Cadastro");
        if(acesso=='E'){
            mensagem.setContentText("O aluno "+nome+" foi cadastrado com sucesso!");
        }else if(acesso=='P'){
            mensagem.setContentText("O professor "+nome+" foi cadastrado com sucesso!");
        }else{
            mensagem.setContentText("O usuário "+nome+" foi cadastrado com sucesso como administrador!");
        }
        mensagem.showAndWait();
    }

    public void mensagemErro(String mensagem){
        Alert erro = new Alert(Alert.AlertType.WARNING);
        erro.setTitle("Aviso");
        erro.setContentText(mensagem);
        erro.showAndWait();
    }

    //Salvar no arquivo
    private void salvarUsuarios() {
        ArrayList<Usuario> tempList = new ArrayList<>(users);
        try {
            ResourceManager.save(tempList, "src/main/resources/Arquivos/listaUsuarios.pjt");
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void salvarAlunos() {
        ArrayList<Aluno> tempList = new ArrayList<>(alunos);
        try {
            ResourceManager.save(tempList, "src/main/resources/Arquivos/listaAlunos.pjt");
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void salvarProfessores() {
        ArrayList<Professor> tempList = new ArrayList<>(professores);
        try {
            ResourceManager.save(tempList, "src/main/resources/Arquivos/listaProfessores.pjt");
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Carregar do Arquivo
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
}
