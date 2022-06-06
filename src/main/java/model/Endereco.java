package model;

import java.io.Serializable;

public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rua;
    private String cep;
    private String numero;
    private String complemento;

    private String cidade;

    public Endereco(){

    }

    public Endereco(String rua, String cep, String numero, String complemento) {
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
