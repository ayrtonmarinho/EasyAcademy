package model;
import java.io.Serializable;

public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numero;
    private String ddd;

    public Telefone() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
