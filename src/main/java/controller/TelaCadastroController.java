package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Endereco;
import model.Telefone;
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
