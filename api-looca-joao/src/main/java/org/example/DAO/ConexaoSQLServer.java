package org.example.DAO;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;

public class ConexaoSQLServer {

    private static final String url = "jdbc:sqlserver://18.232.10.255:1433;databaseName=ConWay;encrypt=false;";
    private static final String user = "sa";
    private static final String password = "urubu100";

    private static final String nameDrive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
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
