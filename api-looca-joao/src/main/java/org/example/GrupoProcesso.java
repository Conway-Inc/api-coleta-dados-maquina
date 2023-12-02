package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.util.List;

public class GrupoProcesso {
    private int idGrupoProcesso;
    private int quantidadeProcesso;
    private String dataHora;
    private String processoUsoCpu;
    private String processoUsoMemoria;
    private int fkTotem;

    public GrupoProcesso(int idGrupoProcesso, int quantidadeProcesso, String dataHora, String processoUsoCpu, String processoUsoMemoria, int fkTotem) {
        this.idGrupoProcesso = idGrupoProcesso;
        this.quantidadeProcesso = quantidadeProcesso;
        this.dataHora = dataHora;
        this.processoUsoCpu = processoUsoCpu;
        this.processoUsoMemoria = processoUsoMemoria;
        this.fkTotem = fkTotem;
    }

    public GrupoProcesso() {
    }

    public Integer quantidadeProcessos (){
        Looca looca = new Looca();

        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();

        return processosGrupo.getTotalProcessos();
    }

    public String getNomeProcessoMaisGastoCpu (){
        Looca looca = new Looca();

        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        List<Processo> processos = processosGrupo.getProcessos();

        double maior = Integer.MIN_VALUE;
        int indiceMaior = -1;
        for (int i = 0; i < processos.size(); i++){
            Double gastoCpu = processos.get(i).getUsoCpu();

            if(gastoCpu > maior){
                indiceMaior = i;
            }

        }

        return processos.get(indiceMaior).getNome();
    }

    public Double getValorProcessoMaisGastoCpu (){
        Looca looca = new Looca();

        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        List<Processo> processos = processosGrupo.getProcessos();

        double maior = Integer.MIN_VALUE;
        for (int i = 0; i < processos.size(); i++){
            Double gastoCpu = processos.get(i).getUsoCpu();

            if(gastoCpu > maior){
                maior = gastoCpu;
            }

        }

        return maior;
    }

    public String getNomeProcessoMaisGastoMemoria (){
        Looca looca = new Looca();

        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        List<Processo> processos = processosGrupo.getProcessos();

        double maior = Integer.MIN_VALUE;
        int indiceMaior = -1;
        for (int i = 0; i < processos.size(); i++){
            Double gastoMemoria = processos.get(i).getUsoMemoria();

            if(gastoMemoria > maior){
                indiceMaior = i;
            }

        }

        return processos.get(indiceMaior).getNome();
    }

    public Double getValorProcessoMaisGastoMemoria (){
        Looca looca = new Looca();

        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        List<Processo> processos = processosGrupo.getProcessos();

        double maior = Integer.MIN_VALUE;
        for (int i = 0; i < processos.size(); i++){
            Double gastoMemoria = processos.get(i).getUsoMemoria();

            if(gastoMemoria > maior){
                maior = gastoMemoria;
            }

        }

        return maior;
    }

    public int getIdGrupoProcesso() {
        return idGrupoProcesso;
    }

    public void setIdGrupoProcesso(int idGrupoProcesso) {
        this.idGrupoProcesso = idGrupoProcesso;
    }

    public int getQuantidadeProcesso() {
        return quantidadeProcesso;
    }

    public void setQuantidadeProcesso(int quantidadeProcesso) {
        this.quantidadeProcesso = quantidadeProcesso;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getProcessoUsoCpu() {
        return processoUsoCpu;
    }

    public void setProcessoUsoCpu(String processoUsoCpu) {
        this.processoUsoCpu = processoUsoCpu;
    }

    public String getProcessoUsoMemoria() {
        return processoUsoMemoria;
    }

    public void setProcessoUsoMemoria(String processoUsoMemoria) {
        this.processoUsoMemoria = processoUsoMemoria;
    }

    public int getFkTotem() {
        return fkTotem;
    }

    public void setFkTotem(int fkTotem) {
        this.fkTotem = fkTotem;
    }
}
