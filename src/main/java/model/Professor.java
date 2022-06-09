package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> turmasLecionadas; //cod.Turma

    public Professor(){

    }

    public Professor(List<Disciplina> discpLec) {
        this.turmasLecionadas = new ArrayList<>(6);
    }

    public Professor(String cpf, String nome, Endereco endereco, List<Disciplina> discpLec) {
        super(cpf, nome, endereco);
        this.turmasLecionadas = new ArrayList<>(6);
    }

    public List<String> getTurmasLecionadas() {
        return turmasLecionadas;
    }
}
