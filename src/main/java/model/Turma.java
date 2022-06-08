package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cod;
    private Disciplina codDisciplina;

    private String nomeDisciplina;
    private String turno;
    private Professor codProfessor;

    private Professor professor;

    private List<Aluno> alunos;

    public Turma() {
        this.alunos = new ArrayList<>(25);

    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCodDisciplina() {
        return codDisciplina.getCodigo();
    }

    public void setCodDisciplina(Disciplina codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Professor getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(Professor codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina() {
        this.nomeDisciplina = codDisciplina.getNome();
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }


}
