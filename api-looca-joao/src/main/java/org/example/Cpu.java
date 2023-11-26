package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class Cpu extends Componente{

    private int ghz;

    public Cpu() {
    }

    public Cpu(int id, String nome, String unidadeMedida, int ghz) {
        super(id, nome, unidadeMedida);
        this.ghz = ghz;
    }

    @Override
    public int getUso() {
        Looca looca = new Looca();

        Processador processador = looca.getProcessador();

        int usoProcessador = processador.getUso().intValue();

        return usoProcessador;
    }

    public int getGhz() {
        return ghz;
    }

    public void setGhz(int ghz) {
        this.ghz = ghz;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "Cpu{" +
                "ghz=" + ghz +
                '}';
    }
}
