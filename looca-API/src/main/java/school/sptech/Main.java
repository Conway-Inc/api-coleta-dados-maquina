package school.sptech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.Servico;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Sistema sistema = looca.getSistema();
        System.out.printf("""
                ------------------------
                Sistema operacional: %s
                ------------------------
                ------------------------
                Tempo de atividade: %s Dias
                ------------------------
                """, sistema.getSistemaOperacional(), (sistema.getTempoDeAtividade()/86400));
        Processador processador = looca.getProcessador();
        System.out.printf("""
                        ------------------------
                        Processador: %s|
                        ------------------------
                        Frequência| Núcleos|Threads
                        %s|     %d  |   %d
                        ------------------------
                        """,processador.getNome(),processador.getFrequencia(),processador.getNumeroCpusFisicas(), processador.getNumeroCpusLogicas());

        Memoria memoria = looca.getMemoria();
        System.out.printf("""
                Memoria
                ------------------------
                Total|  Em uso  | Disponível
                %.2sGB |  %.4sGB  |    %.3sGB
                ------------------------
                """, memoria.getTotal()/Math.pow(10,9),
                memoria.getEmUso()/Math.pow(10,9),
                memoria.getDisponivel()/Math.pow(10,9));




        DiscoGrupo discoGrupo = looca.getGrupoDeDiscos();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        System.out.println("Discos");
        for (Disco disco : discos) {
            System.out.printf("""
                    ------------------------
                    %s
                    Total
                    %.4sGB
                    ------------------------
                    """,disco.getNome() ,disco.getTamanho()/Math.pow(10,9));
        }

        /*
        * A lista de serviços traz os serviços do sistema operacional como conexão wifi, sistemas de segurança etc
        * retornando o seu nome, estado (RUNNING, STOPPED) e seu ID
        * */
        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        ServicoGrupo servicoGrupo = looca.getGrupoDeServicos();
        List<Volume> volumes = grupoDeDiscos.getVolumes();
        List<Processo> processos = processosGrupo.getProcessos();
        List<Servico> servicos = servicoGrupo.getServicos();

    }
}