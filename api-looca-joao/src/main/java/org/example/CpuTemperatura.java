package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

public class CpuTemperatura extends Componente{
    public CpuTemperatura() {
    }

    public CpuTemperatura(int id, String nome, String unidadeMedida) {
        super(id, nome, unidadeMedida);
    }

    public Double getTemperaturaCpu(){
        Looca looca = new Looca();
        Temperatura temperatura = new Temperatura();
        double valorTemperatura = temperatura.getTemperatura();

        return valorTemperatura;
    }
}
