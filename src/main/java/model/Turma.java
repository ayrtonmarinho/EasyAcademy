package model;

import java.io.Serializable;

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cod;
    private Disciplina codDisciplina;

    private String nomeDisciplina;
    private String turno;
    private Professor codProfessor;

    public Turma() {

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
}
