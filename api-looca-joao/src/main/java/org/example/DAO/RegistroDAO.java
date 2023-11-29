package org.example.DAO;

import org.example.Registro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
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


    public void inserirRegistrosTotem(int idTotem, int usoCpu, int usoMemoria, int usoDisco, double temperatura){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);

        String mySql = "CALL inserirDadosTotemID (?, 'Memória', ?, 'CPU', ?, 'Disco', ?,'TemperaturaCpu', ?, NOW());";
        String sqlServer = "EXEC inserirDadosTotemID @idTotem = ?, @co1_nome = 'Memória', @re1_valor = ?, @co2_nome = 'CPU', @re2_valor = ?, @co3_nome = 'Disco', @re3_valor = ?, @co4_nome = 'TemperaturaCpu' , @re4_valor = ?, @re_data = '" + formattedDateTime + "';";
        conMysql.update(mySql, idTotem, usoMemoria, usoCpu, usoDisco, temperatura);
        conSqlServer.update(sqlServer, idTotem, usoMemoria, usoCpu, usoDisco, temperatura);
    }

    public int getIdTotemUltimoRegistro(int fkTotem, int fkComponente){
            /*String mySql = "SELECT idRegistro FROM Registro WHERE fkTotem = " + fkTotem + " AND fkComponente = " + fkComponente +" ORDER BY dataHora DESC LIMIT 1";*/
        String sqlServer = "SELECT TOP 1 idRegistro FROM Registro WHERE fkTotem = " + fkTotem + " AND fkComponente = " + fkComponente + " ORDER BY dataHora DESC;";

        /*List<Registro> idRegistro = conMysql.query(sql, new BeanPropertyRowMapper<>(Registro.class));*/
        List<Registro> idRegistroSqlServer = conSqlServer.query(sqlServer, new BeanPropertyRowMapper<>(Registro.class));

        return idRegistroSqlServer.get(0).getIdRegistro();
    }
}
