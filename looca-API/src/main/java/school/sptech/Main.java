package school.sptech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
//import com.github.britooo.looca.api.group.servicos.Servico;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ConexaoMysql conexao = new ConexaoMysql();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        LocalDateTime dataHora = LocalDateTime.now();


        Looca looca = new Looca();

        Sistema sistema = looca.getSistema();
        Processador processador = looca.getProcessador();
        Memoria memoria = looca.getMemoria();
        System.out.printf("""
                ------------------------
                Sistema operacional: %s
                ------------------------w
                Tempo de atividade: %s Dias
                ------------------------
                Processador: %s
                Frequência| Núcleos|Threads
                %s GHz |   %d    |   %d
                ------------------------
                Memoria
                Total|  Em uso  | Disponível
                %.2sGB |  %.4sGB  |    %.3sGB
                ------------------------
                """,
                sistema.getSistemaOperacional(),
                sistema.getTempoDeAtividade()/86400,
                processador.getNome(),
                processador.getFrequencia()/Math.pow(10,9),
                processador.getNumeroCpusFisicas(),
                processador.getNumeroCpusLogicas(),
                memoria.getTotal()/Math.pow(10,9),
                (memoria.getEmUso()/Math.pow(10,9))-1,
                memoria.getDisponivel()/Math.pow(10,9)
        );

        con.update("INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?)",
                Math.ceil(processador.getUso()), dataHora, 1, 1);

        con.update("INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?)",
                (Math.ceil(memoria.getEmUso()/Math.pow(10,9))/Math.ceil(memoria.getTotal()/Math.pow(10,9)) * 100)
                , dataHora, 2, 1);

        // DiscoGrupo discoGrupo = looca.getGrupoDeDiscos();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        List<Object> nomeDisco = new ArrayList<>();
        List<Double> tamanhoDisco = new ArrayList<>();

        List<Volume> volumes = grupoDeDiscos.getVolumes();
        List<Double> volumeDisponivel = new ArrayList<>();

        for (Disco disco : discos) {
            nomeDisco.add(disco.getNome());
            tamanhoDisco.add(disco.getTamanho().doubleValue());
        }
        for (Volume volume : volumes){
            volumeDisponivel.add(volume.getDisponivel().doubleValue());
        }

        System.out.println("Discos");

        for (int i = 0; i < nomeDisco.size(); i++) {
            System.out.printf("""
                    %s
                    Total
                    %.5sGB
                    Disponível
                    %.5sGB
                    ------------------------
                    """,nomeDisco.get(i),
                    tamanhoDisco.get(i)/Math.pow(10,9),
                    volumeDisponivel.get(i)/Math.pow(10,9)
            );

            con.update("INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?)",
                    volumeDisponivel.get(i)/Math.pow(10,9), dataHora, 4, 1);

        }



        ServicoGrupo servicoGrupo = looca.getGrupoDeServicos();
        // List<Servico> servicos = servicoGrupo.getServicos();
        ProcessoGrupo processosGrupo = looca.getGrupoDeProcessos();
        List<Processo> processos = processosGrupo.getProcessos();
        System.out.print("""
                     PID    | Processo
                     ------------------
                    """);
        for (Processo processo: processos){
            System.out.printf("""
                    %s    |    %s
                    ------------------
                    """, processo.getPid(), processo.getNome());
        }
        System.out.println("Total de processos: " + processosGrupo.getTotalProcessos());
        System.out.println("Total de serviços: " + servicoGrupo.getTotalServicosAtivos());
    }
}
