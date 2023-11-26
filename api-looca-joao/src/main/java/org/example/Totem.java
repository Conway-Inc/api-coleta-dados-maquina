package org.example;

public class Totem {

    private int idTotem;
    private String nome;
    private int fkAeroporto;
    private int fkEmpresa;

    public Totem(){};

    public Totem(int idTotem, String nome, int fkAeroporto, int fkEmpresa) {
        this.idTotem = idTotem;
        this.nome = nome;
        this.fkAeroporto = fkAeroporto;
        this.fkEmpresa = fkEmpresa;
    }

    public int getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(int idTotem) {
        this.idTotem = idTotem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFkAeroporto() {
        return fkAeroporto;
    }

    public void setFkAeroporto(int fkAeroporto) {
        this.fkAeroporto = fkAeroporto;
    }

    public int getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(int fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "ID: " + idTotem + " - Nome: " + nome + "\n";
    }
}
