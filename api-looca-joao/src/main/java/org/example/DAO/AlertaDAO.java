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

        String sql = "INSERT INTO Alerta (tipo, descricao, fkRegistro) VALUES (?, ?, ?);";

        conMysql.update(sql, tipoAlerta, descricao, registroDAO.getIdTotemUltimoRegistro(fkTotem, fkComponente));
    }

}
