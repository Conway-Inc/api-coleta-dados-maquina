package org.example;

import com.github.britooo.looca.api.core.Looca;

public class Memoria extends Componente {


    public Memoria() {
    }

    public Memoria(int id, String nome, String unidadeMedida) {
        super(id, nome, unidadeMedida);
    }

    @Override
    public int getUso() {

        Looca looca = new Looca();

        com.github.britooo.looca.api.group.memoria.Memoria memoria = looca.getMemoria();

        int usoMemoria = (int) ((memoria.getEmUso() * 100) / memoria.getTotal());

        return usoMemoria;
    }

    public int getPercentualUso(Double valorEmUso, Double valorTotal){

        Double percentualUso = (valorEmUso*100)/valorTotal;

        return percentualUso.intValue();
    }
}
