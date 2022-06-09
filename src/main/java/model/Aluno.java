package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int  creditos;
    private List<String> turmas; //cod.Turma
    public Aluno(){

    }



    public Aluno(String cpf, String nome, Endereco endereco, Disciplina discpMat) {
        super(cpf, nome, endereco);
        this.turmas = new ArrayList<>(8);
        this.creditos = 24;
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

    public List<String> getTurmas() {
        return turmas;
    }
}
