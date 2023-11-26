package org.example.DAO;

import org.example.Registro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RegistroDAO {

    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;

    public RegistroDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }


    public void inserirRegistrosTotem(int idTotem, int usoCpu, int usoMemoria, int usoDisco){
        String sql = "CALL inserirDadosTotemID (?, 'Memória', ?, 'CPU', ?, 'Disco', ?, NOW());";
        conMysql.update(sql, idTotem, usoMemoria, usoCpu, usoDisco);
    }

    public int getIdTotemUltimoRegistro(int fkTotem, int fkComponente){
        String sql = "SELECT idRegistro FROM Registro WHERE fkTotem = " + fkTotem + " AND fkComponente = " + fkComponente +" ORDER BY dataHora DESC LIMIT 1";

        List<Registro> idRegistro = conMysql.query(sql, new BeanPropertyRowMapper<>(Registro.class));

        return idRegistro.get(0).getIdRegistro();
    }
}
