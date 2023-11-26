package org.example;

public class Registro {

    private int idRegistro;
    private int valor;
    private String dataHora;
    private int fkComponente;
    private int fkTotem;

    public Registro(){}

    public Registro(int idRegistro, int valor, String dataHora, int fkComponente, int fkTotem) {
        this.idRegistro = idRegistro;
        this.valor = valor;
        this.dataHora = dataHora;
        this.fkComponente = fkComponente;
        this.fkTotem = fkTotem;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(int fkComponente) {
        this.fkComponente = fkComponente;
    }

    public int getFkTotem() {
        return fkTotem;
    }

    public void setFkTotem(int fkTotem) {
        this.fkTotem = fkTotem;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "idRegistro=" + idRegistro +
                ", valor=" + valor +
                ", dataHora='" + dataHora + '\'' +
                ", fkComponente=" + fkComponente +
                ", fkTotem=" + fkTotem +
                '}';
    }
}
