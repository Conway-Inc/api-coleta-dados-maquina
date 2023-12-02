package org.example.DAO;

import com.github.britooo.looca.api.group.processos.Processo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GrupoProcessoDAO {
    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;
    public GrupoProcessoDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }

    public void inserirProcessos(int idTotem,  int quantidadeProcesso, String processoUsoCpu, String processoUsoMemoria){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);

        String sql = "INSERT INTO GrupoProcesso (quantidadeProcesso, dataHora, processoUsoCpu, processoUsoMemoria, fkTotem) VALUES (?, ?, ?, ?)";
        String sqLServer = "INSERT INTO GrupoProcesso (quantidadeProcesso, dataHora, processoUsoCpu, processoUsoMemoria, fkTotem) VALUES (?, ?, ?, ?)";

        conMysql.update(sql, quantidadeProcesso, formattedDateTime, processoUsoCpu, processoUsoMemoria, idTotem);
        conSqlServer.update(sqLServer, quantidadeProcesso, formattedDateTime, processoUsoCpu, processoUsoMemoria, idTotem);

    }

}
