package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Disciplina> discpMat; // Disciplinas matriculadas no semestre vigente

    private int  creditos;
    public Aluno(){

    }

    public Aluno(List<Disciplina> discpMat) {
        this.discpMat = discpMat;
    }

    public Aluno(String cpf, String nome, Endereco endereco, Disciplina discpMat) {
        super(cpf, nome, endereco);
        this.discpMat = new ArrayList<>();
        this.creditos = 24;
    }

    public List<Disciplina> getDiscpMat() {
        return discpMat;
    }

    public void setDiscpMat(Disciplina discpMat) {
        this.discpMat.add(discpMat);
    }

    public int getCreditos() {
        return creditos;
    }

    public boolean subCredito(int valor){
        if(this.creditos>=valor){
            this.creditos -= valor;
            return true;
        }
        return false;
    }
}
