package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;

import java.util.List;

public class Disco extends Componente  {

    public Disco() {
    }

    public Disco(int id, String nome, String unidadeMedida) {
        super(id, nome, unidadeMedida);
    }

    @Override
    public int getUso() {
        Looca looca = new Looca();

        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        List<Volume> volumes = grupoDeDiscos.getVolumes();

        Double usoDisco = 0.0;
        Double volumeTotalDiscos = 0.0;
        Double volumeDisponivelDiscos = 0.0;

        for (Volume volume : volumes) {
            volumeTotalDiscos += volume.getTotal();
            volumeDisponivelDiscos += volume.getDisponivel();
        }

        int usoDiscos = (int) (((volumeTotalDiscos - volumeDisponivelDiscos) * 100) / volumeTotalDiscos);

        return usoDiscos;
    }
}
