package org.example.DAO;

import org.example.Totem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TotemDAO {

    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;

    public TotemDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }

    public String getTotens(int fkEmpresa){

        String sql = "SELECT * FROM Totem WHERE fkEmpresa = " + fkEmpresa;

        List<Totem> totens = conSqlServer.query(sql, new BeanPropertyRowMapper<>(Totem.class));

        return totens.toString();

    }

    public Totem getTotemID (int idTotem, int idEmpresa){

        String sql = "SELECT * FROM Totem WHERE idTotem = " + idTotem + " AND fkEmpresa = " + idEmpresa;

        List<Totem> totem = conSqlServer.query(sql, new BeanPropertyRowMapper<>(Totem.class));

        if (totem.isEmpty()){

            System.out.println("Totem não encontrado! \n");

            return null;
        } else {
            return totem.get(0);
        }


    }
}
