package org.example.DAO;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

public class ConexaoSQLServer {

    private static final String url = "jdbc:sqlserver://localhost:3306/ConWay";
    private static final String user = "user_conway";
    private static final String password = "urubu100";

    private static final String nameDrive = "com.sqlserver.cj.jdbc.Driver";
    private static Connection conexao;

    private JdbcTemplate conexaoBanco;

    public ConexaoSQLServer(){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(nameDrive);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        conexaoBanco = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConexao() {
        return conexaoBanco;
    }


}
