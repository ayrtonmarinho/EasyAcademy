package model;

import java.io.Serializable;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nome;
    private String periodo;

    public Disciplina(){

    }

    public Disciplina(String codigo, String descricao, String periodo) {
        this.codigo = codigo;
        this.nome = descricao;
        this.periodo = periodo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
