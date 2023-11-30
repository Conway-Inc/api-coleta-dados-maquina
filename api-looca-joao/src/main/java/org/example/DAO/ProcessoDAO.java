package org.example.DAO;

import com.github.britooo.looca.api.group.processos.Processo;
import org.example.Registro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProcessoDAO {
    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;
    public ProcessoDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }

    public Integer selectUltimoRegistro(){
        String sql = "SELECT idRegistro FROM Registro ORDER BY idRegistro DESC LIMIT 1";

        List<Registro> idRegistro = conMysql.query(sql, new BeanPropertyRowMapper<>(Registro.class));

        int ultimoRegistro = 0;

        for (Registro registro : idRegistro) {
            ultimoRegistro = registro.getIdRegistro();
        }

        return ultimoRegistro;
    }
    public void inserirProcessos(List<Processo> processos){
        ProcessoDAO pdao = new ProcessoDAO();

        String sql = "INSERT INTO Processo VALUES (NULL, ?, ?, ?)";
        for(Processo processoDaVez: processos){
            conSqlServer.update(sql, processoDaVez.getPid(), processoDaVez.getNome(), pdao.selectUltimoRegistro());
            conMysql.update(sql, processoDaVez.getPid(), processoDaVez.getNome(), pdao.selectUltimoRegistro());
        }
    }

}
