package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private Endereco endereco;

    private char acesso;

    private List<Telefone> telefone;
    private String senha;

    public Usuario() {
        this.telefone = new ArrayList<>();

    }

    public Usuario(String cpf, String nome, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone.add(telefone);
    }

    public char getAcesso() {
        return acesso;
    }

    public void setAcesso(char acesso) {
        this.acesso = acesso;
    }
}
