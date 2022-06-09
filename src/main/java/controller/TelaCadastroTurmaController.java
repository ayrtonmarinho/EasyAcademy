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
import model.Disciplina;
import model.Professor;
import model.Turma;
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

public class TelaCadastroTurmaController implements Initializable {
    private Usuario user;

    @FXML
    private ToggleGroup toggleTurno;
    @FXML
    private Label desenvolvedores;
    @FXML
    private Label nomeAdmin;
    @FXML
    private Label matricula;
    @FXML
    private TextField codigoTurma;
    @FXML
    private Label codigoDisciplina;
    @FXML
    private ComboBox<Professor> cb_professor;
    @FXML
    private ComboBox<String> cb_horario;
    //Tables Disciplina
    @FXML
    private TableView<Disciplina> table_disciplina;
    @FXML
    private TableColumn<Disciplina, String> col_codigo;
    @FXML
    private TableColumn<Disciplina, String> col_nome;
    @FXML
    private TableColumn<Disciplina, String> col_cargaHoraria;
    @FXML
    private TableColumn<Disciplina, Integer> col_creditos;
    @FXML
    private TableColumn<Disciplina, String> col_periodo;

    //Files
    File fileT = new File("src/main/resources/Arquivos/listaTurmas.pjt");
    File fileD = new File("src/main/resources/Arquivos/listaDisciplinas.pjt");

    //Lista
    private ObservableList<Turma> turmas = FXCollections.observableArrayList();
    private ObservableList<Disciplina> disciplinas = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Files
        if(fileD.exists()){
            disciplinas = carregarDisciplinas();
        }
        if (fileT.exists()){
            turmas = carregarTurmas();
        }

        //Set ComboBox Horario
        cb_horario.setPromptText("Selecione um horario");

        //Tabela Disciplina
        col_codigo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("codigo"));
        col_nome.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("nome"));
        col_cargaHoraria.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("cargaHoraria"));
        col_creditos.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("creditos"));
        col_periodo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("periodo"));

        table_disciplina.setPlaceholder(new Label("Não há disciplinas cadastradas."));
        table_disciplina.setItems(disciplinas);

        table_disciplina.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> addDisciplina(newValue));
    }

    public void addDisciplina(Disciplina disciplina) {
        if (disciplina != null) {
            codigoDisciplina.setText(disciplina.getCodigo());
            toggleTurno.selectToggle(null);
            codigoTurma.clear();
            cb_horario.getItems().clear();
            cb_horario.setPromptText("Selecione um horário");
        }
    }


    //Cadastrar turma
    public void cadastrarTurma(){
            int selectedIndex = table_disciplina.getSelectionModel().getSelectedIndex();
            if(selectedIndex>=0){
                if(validarCampos()){
                    Disciplina disciplina = table_disciplina.getSelectionModel().getSelectedItem();
                    RadioButton turno = (RadioButton) toggleTurno.getSelectedToggle();
                    Turma turma = new Turma();
                    turma.setCod(codigoTurma.getText());
                    turma.setCodDisciplina(disciplina);
                    turma.setTurno(turno.getText());
                    turma.setHorario(cb_horario.getSelectionModel().getSelectedItem());
                    if (jaExiste(turma)){
                        Alertas.alertaErro("Cadastrar Turma","Cod.Turma ja existe.","");
                    }else{
                        turmas.add(turma);
                        salvarTurmas();
                        Alertas.alertaInformation("Cadastro de Turma","A turma "+turma.getCod()+" foi cadastrado com " +
                                "sucesso. ","");
                    }
                }
            }else {
                Alertas.alertaErro("Cadastro de Turma","Selecione alguma disciplina","");
            }
    }


    public void setHorario(){
        RadioButton turno = (RadioButton) toggleTurno.getSelectedToggle();
        if(turno.getText().equalsIgnoreCase("Manhã")){
            cb_horario.getItems().clear();
            cb_horario.setPromptText("Selecione um horário");
            cb_horario.getItems().addAll("2-AB","2-CD","2-EF","3-AB","3-CD","3-EF","4-AB","4-CD","4-EF","5-AB","5-CD"
                    ,"5-EF","6-AB","6-CD","6-EF");
        }else if(turno.getText().equalsIgnoreCase("Tarde")){
            cb_horario.getItems().clear();
            cb_horario.setPromptText("Selecione um horário");
            cb_horario.getItems().addAll("2-GH","2-IJ","2-LM","3-GH","3-IJ","3-LM","4-GH","4-IJ","4-LM","5-GH","5-IJ"
                    ,"5-LM","6-GH","6-IJ","6-LM");
        }else{
            cb_horario.getItems().clear();
            cb_horario.setPromptText("Selecione um horário");
            cb_horario.getItems().addAll("2-NO","2-PQ","3-NO","3-PQ","4-NO","4-PQ","5-NO","5-PQ"
                    ,"6-NO","6-PQ");
        }


    }

    //Validar
    public boolean validarCampos(){
        StringBuilder msgErro = new StringBuilder();
        if(codigoTurma.getText().isEmpty() &&  codigoTurma.getText().isBlank()){
            msgErro.append("Codigo da turma esta vazio.");
        }
        if(cb_horario.getSelectionModel().isSelected(-1)){
            msgErro.append("Horário não selecionado");
        }
        if(msgErro.isEmpty()){
            return true;
        }else{
            Alertas.alertaErro("Cadastro de Turma",msgErro.toString(),"");
            return false;
        }
    }

    private boolean jaExiste(Turma turma){
        for(Turma t:turmas){
            if(t.getCod().compareToIgnoreCase(turma.getCod())==0){
                return true;
            }
        }
        return false;
    }

    //Salvar e Carregar disciplina
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

    //Salvar e Carregar turmas
    private ObservableList<Turma> carregarTurmas() {
        try {
            List<Turma> list = (List<Turma>) ResourceManager.load("src/main/resources/Arquivos/listaTurmas.pjt");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    private void salvarTurmas() {
        ArrayList<Turma> tempList = new ArrayList<>(turmas);
        try {
            ResourceManager.save(tempList, "src/main/resources/Arquivos/listaTurmas.pjt");
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initData(Usuario usuario){
        user = usuario;
        nomeAdmin.setText(user.getNome());
        matricula.setText(user.getCpf());
        setHorario();
    }

    //Retorna para tela admin
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
