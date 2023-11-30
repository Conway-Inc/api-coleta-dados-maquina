package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;

import java.util.List;

public class Processo {
    private int idProcesso;
    private int pid;

    private String nome;

    private int fkRegistro;

    public Processo() {

    }

    public Processo(int idProcesso, int pid, String nome, int fkRegistro) {
        this.idProcesso = idProcesso;
        this.pid = pid;
        this.nome = nome;
        this.fkRegistro = fkRegistro;
    }

    public List<com.github.britooo.looca.api.group.processos.Processo> listarProcessos (){
        Looca looca = new Looca();

        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        List<com.github.britooo.looca.api.group.processos.Processo> processos = processosGrupo.getProcessos();

        return processos;
    }

    public int getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(int idProcesso) {
        this.idProcesso = idProcesso;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFkRegistro() {
        return fkRegistro;
    }

    public void setFkRegistro(int fkRegistro) {
        this.fkRegistro = fkRegistro;
    }
}
