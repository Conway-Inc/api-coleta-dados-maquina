package org.example.DAO;

import org.example.Componente;
import org.example.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FuncionarioDAO {

    JdbcTemplate conMysql;
    JdbcTemplate conSqlServer;

    public FuncionarioDAO() {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        ConexaoSQLServer conexaoSQLServer = new ConexaoSQLServer();
        this.conMysql = conexaoMysql.getConexao();
        this.conSqlServer = conexaoSQLServer.getConexao();
    }

    public Funcionario autenticar(String email, String senha){

        String sql = "SELECT * FROM Funcionario WHERE email = '" + email + "' AND senha = '" + senha + "'";

        List<Funcionario> funcionario = conSqlServer.query(sql, new BeanPropertyRowMapper<>(Funcionario.class));

        if (funcionario.isEmpty()){

            System.out.println("\nEmail e/ou senha incorreto(s)!");

            return null;
        } else {

            System.out.println("\nLogin feito com sucesso!");

            return funcionario.get(0);
        }

    }
}
