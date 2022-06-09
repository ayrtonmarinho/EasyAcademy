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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Aluno;
import model.Disciplina;
import model.Usuario;
import utils.Alertas;
import utils.ResourceManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCadastroDisciplinaController implements Initializable {
    private Usuario user;
    @FXML
    private Label nomeAdmin;
    @FXML
    private Label matricula;

    @FXML
    private TextField nomeDisciplina;

    @FXML
    private TextField codigoDisciplina;

    @FXML
    private ComboBox<String> cb_periodo;

    @FXML
    private RadioButton radio_30;

    @FXML
    private RadioButton radio_60;

    @FXML
    private RadioButton radio_120;

    private ToggleGroup cargaHoraria;

    //Table disciplinas
    @FXML
    private TableView<Disciplina> table_disciplina;

    @FXML
    private TableColumn<Disciplina, String> col_codigo;

    @FXML
    private TableColumn<Disciplina, String> col_nome;

    @FXML
    private TableColumn<Disciplina, String> col_cargaH;

    @FXML
    private TableColumn<Disciplina, Integer> col_creditos;

    @FXML
    private TableColumn<Disciplina, String> col_periodo;

    //Lista
    ObservableList<Disciplina> disciplinas = FXCollections.observableArrayList();
    //File
    File file = new File("src/main/resources/Arquivos/listaDisciplinas.pjt");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(file.exists()){
            disciplinas = carregarDisciplinas();
        }
        //Set Itens para o ComboBox Periodo
        cb_periodo.setPromptText("Selecione um período");
        cb_periodo.getItems().addAll("1º Período","2º Período","3º Período","4º Período","5º Período","6º Período","7º Período","8º Período");

        //Tabela Disciplina
        col_codigo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("codigo"));
        col_nome.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("nome"));
        col_cargaH.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("cargaHoraria"));
        col_creditos.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("creditos"));
        col_periodo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("periodo"));

        table_disciplina.setPlaceholder(new Label("Não há disciplinas cadastradas."));
        table_disciplina.setItems(disciplinas);

    }

    public void cadastrarDisciplina(){
        if (validarCampos()){
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(nomeDisciplina.getText());
            disciplina.setCodigo(codigoDisciplina.getText());
            disciplina.setCargaHoraria(selectedHorario());
            disciplina.setCreditos(creditos());
            disciplina.setPeriodo(cb_periodo.getSelectionModel().getSelectedItem());
            if (jaExiste(disciplina)){
                Alertas.alertaErro("Cadastro de Disciplina","O cod.Disciplina "+disciplina.getCodigo()+" já esta cadastrado.","Erro: Codigo já cadastrado.");
            }else{
                disciplinas.add(disciplina);
                clearFields();
                Alertas.alertaInformation("Cadastro de Disciplina","A disciplina "+disciplina.getCodigo()+" | "+disciplina.getNome()+" foi cadastrada com sucesso.","");
                salvarDisciplinas();
            }
        }
    }

    private int  creditos(){
        if (radio_30.isSelected()){
            return 2;
        }else if (radio_60.isSelected()){
            return 4;
        }
        return 6;

    }

    private String selectedHorario(){
        if(radio_30.isSelected()){
            return radio_30.getText();
        }else if(radio_60.isSelected()){
            return radio_60.getText();
        }else{
            return radio_120.getText();
        }
    }

    private boolean jaExiste(Disciplina disciplina){
        for(Disciplina d:disciplinas){
            if(d.getCodigo().compareToIgnoreCase(disciplina.getCodigo())==0){
                return true;
            }
        }
        return false;
    }

    private boolean validarCampos(){
        StringBuilder msgCampos = new StringBuilder();
        if(nomeDisciplina.getText().isEmpty() && nomeDisciplina.getText().isBlank()){
            msgCampos.append("Nome de disciplina esta vazio\n");
        }
        if(codigoDisciplina.getText().isEmpty() && codigoDisciplina.getText().isBlank()){
            msgCampos.append("Codigo de disciplina está vazio\n");
        }
        if(cb_periodo.getSelectionModel().isEmpty()){
            msgCampos.append("Selecione algum periodo\n");
        }

        if (msgCampos.isEmpty()){
            return  true;
        }else{
            Alertas.alertaErro("Campos Invalidados",msgCampos.toString(),"O seguinte(s) campo(s) estão inválidos:\n");
            return false;
        }
    }

    private void clearFields(){
        nomeDisciplina.clear();
        codigoDisciplina.clear();
        cb_periodo.getSelectionModel().clearSelection();
        cb_periodo.setPromptText("Selecione um periodo");
        //cargaHoraria.selectToggle(null);
        radio_60.isSelected();

    }

    public void initData(Usuario usuario){
        user = usuario;
        nomeAdmin.setText(user.getNome());
        matricula.setText(user.getCpf());
    }

    // Carregar e Salvar
    private ObservableList<Disciplina> carregarDisciplinas() {
        try {
            List<Disciplina> list = (List<Disciplina>) ResourceManager.load("src/main/resources/Arquivos/listaDisciplinas.pjt");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    private void salvarDisciplinas() {
        ArrayList<Disciplina> tempList = new ArrayList<>(disciplinas);
        try {
            ResourceManager.save(tempList, "src/main/resources/Arquivos/listaDisciplinas.pjt");
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //Retornar a admin
    public void voltar(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getClassLoader().getResource("view/TelaAdmin.fxml"));
        Parent novaCenaParent = fxmloader.load();
        Scene novaCena = new Scene(novaCenaParent);
        TelaAdminController controller = fxmloader.getController();
        user.setNome(nomeAdmin.getText());
        user.setCpf(matricula.getText());
        controller.initData(user);
        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //novaCena.setFill(javafx.scene.paint.Color.TRANSPARENT);
        window.setScene(novaCena);
        window.show();
    }

}
