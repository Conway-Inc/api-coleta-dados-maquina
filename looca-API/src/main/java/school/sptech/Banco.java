package school.sptech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    public static void main(String[] args) throws InterruptedException {
        int contador = 0;

        ConexaoMysql conexao = new ConexaoMysql();
        JdbcTemplate con = conexao.getConexaoDoBanco();


        while(contador <= 99){
            LocalDateTime dataHora = LocalDateTime.now();

            Looca looca = new Looca();

            Processador processador = looca.getProcessador();
            Memoria memoria = looca.getMemoria();

            contador += 1;

            con.update("INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?)",
                    Math.ceil(processador.getUso()), dataHora, 1, 1);

            con.update("INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?)",
                    (Math.ceil(memoria.getEmUso()/Math.pow(10,9))/Math.ceil(memoria.getTotal()/Math.pow(10,9)) * 100)
                    , dataHora, 2, 1);

            DiscoGrupo grupoDeDiscos = new DiscoGrupo();
            List<Disco> discos = grupoDeDiscos.getDiscos();
            List<Double> tamanhoDisco = new ArrayList<>();

            List<Volume> volumes = grupoDeDiscos.getVolumes();
            List<Double> volumeDisponivel = new ArrayList<>();

            for (Disco disco : discos) {
                tamanhoDisco.add(disco.getTamanho().doubleValue());
            }
            for (Volume volume : volumes){
                volumeDisponivel.add(volume.getDisponivel().doubleValue());
            }

            for (int i = 0; i < tamanhoDisco.size(); i++) {
                con.update("INSERT INTO Registro (valor, dataHora, fkComponente, fkTotem) VALUES (?, ?, ?, ?)",
                        volumeDisponivel.get(i)/Math.pow(10,9), dataHora, 4, 1);
            }

            System.out.println("INSERT DE NÙMERO " + contador);

            Thread.sleep(1000);
        }
    }
}
