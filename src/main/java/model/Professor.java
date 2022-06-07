package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Disciplina> discpLec; // Lista de disciplinas lecionadas no semestre vigente

    private int cadeiras;

    public Professor(List<Disciplina> discpLec) {
        this.discpLec = new ArrayList<>(8);
    }

    public Professor(String cpf, String nome, Endereco endereco, List<Disciplina> discpLec) {
        super(cpf, nome, endereco);
        this.discpLec = new ArrayList<>(8);
    }

    public List<Disciplina> getDiscpLec() {
        return discpLec;
    }

    public void setDiscpLec(List<Disciplina> discpLec) {
        this.discpLec = discpLec;
    }
}
