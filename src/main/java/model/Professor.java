package model;
import java.io.Serializable;
import java.util.List;

public class Professor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String matricula;
    private List<Disciplina> discpLec; // Lista de disciplinas lecionadas no semestre vigente

    public Professor(String matricula, List<Disciplina> discpLec) {
        this.matricula = matricula;
        this.discpLec = discpLec;
    }

    public Professor(int cpf, String nome, Endereco endereco, String matricula, List<Disciplina> discpLec) {
        super(cpf, nome, endereco);
        this.matricula = matricula;
        this.discpLec = discpLec;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDiscpLec() {
        return discpLec;
    }

    public void setDiscpLec(List<Disciplina> discpLec) {
        this.discpLec = discpLec;
    }
}
