package org.example.DAO;

import org.example.Componente;
import org.example.ComponenteRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ComponenteDAO {

    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;

    public ComponenteDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }

    public List<Componente> consultarComponentes(){
        String sql = "SELECT * FROM Componente";
        List<Componente> componentes = conMysql.query(sql, new ComponenteRowMapper());
        return componentes;
    }


}
