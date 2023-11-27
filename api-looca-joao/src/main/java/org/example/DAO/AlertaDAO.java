package org.example.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

public class AlertaDAO {

    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;

    public AlertaDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }

    public void inserirAlerta (int tipoAlerta, String descricao, int fkTotem, int fkComponente){
        RegistroDAO registroDAO = new RegistroDAO();

        String sqlServer = "INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (?, ?, ?);";
        String mySql = "INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (?, ?, (SELECT idRegistro FROM Registro WHERE fkTotem = " + fkTotem + " AND fkComponente = " + fkComponente + " ORDER BY dataHora DESC LIMIT 1));";

        conSqlServer.update(sqlServer, tipoAlerta, descricao, registroDAO.getIdTotemUltimoRegistro(fkTotem, fkComponente));
        conMysql.update(mySql, tipoAlerta, descricao);

    }

}
