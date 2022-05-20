package model;

public class Disciplina {
    private String codigo;
    private String descricao;
    private String periodo;

    public Disciplina(){

    }

    public Disciplina(String codigo, String descricao, String periodo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.periodo = periodo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
