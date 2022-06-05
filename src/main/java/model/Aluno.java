package model;
import java.io.Serializable;
import java.util.List;

public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String matricula;
    private List<Disciplina> discpMat; // Disciplinas matriculadas no semestre vigente

    public Aluno(){

    }

    public Aluno(String matricula, List<Disciplina> discpMat) {
        this.matricula = matricula;
        this.discpMat = discpMat;
    }

    public Aluno(int cpf, String nome, Endereco endereco, String matricula, List<Disciplina> discpMat) {
        super(cpf, nome, endereco);
        this.matricula = matricula;
        this.discpMat = discpMat;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDiscpMat() {
        return discpMat;
    }

    public void setDiscpMat(List<Disciplina> discpMat) {
        this.discpMat = discpMat;
    }
}
