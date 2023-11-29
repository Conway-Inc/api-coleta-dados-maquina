package school.sptech;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class ConexaoMysql {

    private static final String url = "jdbc:mysql://localhost:3306/ConWay";
    private static final String user = "user_conway";
    private static final String password = "urubu100";
    private static final String nameDrive = "com.mysql.cj.jdbc.Driver";
    private JdbcTemplate conexaoDoBanco;

    public ConexaoMysql() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(nameDrive);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }

    public void consultarETEnviarParaSlack() {
        String sql = "SELECT " +
                "M.idManutenção, M.dataManutencao, M.dataLimite, " +
                "M.motivoManutencao, M.urgenciaManutencao, M.descricaoManutencao, " +
                "M.valor, T.nome, M.aprovado, M.dataAtual, " +
                "CASE " +
                "    WHEN M.dataAtual < M.dataManutencao THEN 'Totem Pendente' " +
                "    WHEN M.dataAtual > M.dataLimite THEN 'Totem Finalizado' " +
                "    WHEN M.dataAtual >= M.dataManutencao AND M.dataAtual < M.dataLimite THEN 'Totem em Andamento' " +
                "    ELSE 'Totem indefinido' " +
                "END AS estadoTotem " +
                "FROM Manutencao M JOIN Totem T ON M.fkTotem = T.idTotem";

        conexaoDoBanco.query(sql, (resultSet, rowNum) -> {
            try {
                int idManutencao = resultSet.getInt("idManutenção");
                Date dataManutencao = resultSet.getDate("dataManutencao");
                Date dataLimite = resultSet.getDate("dataLimite");
                String motivoManutencao = resultSet.getString("motivoManutencao");
                String urgenciaManutencao = resultSet.getString("urgenciaManutencao");
                String descricaoManutencao = resultSet.getString("descricaoManutencao");
                BigDecimal valor = resultSet.getBigDecimal("valor");
                String nomeTotem = resultSet.getString("nome");
                boolean aprovado = resultSet.getBoolean("aprovado");
                Date dataAtual = resultSet.getDate("dataAtual");
                String estadoTotem = resultSet.getString("estadoTotem");

                if (((java.sql.Date) dataLimite).toLocalDate().isAfter(LocalDate.now())) {
                    StringBuilder mensagem = new StringBuilder();
                    mensagem.append("idManutencao: ").append(idManutencao).append("\n");
                    mensagem.append("dataManutencao: ").append(dataManutencao).append("\n");
                    mensagem.append("dataLimite: ").append(dataLimite).append("\n");
                    mensagem.append("motivoManutencao: ").append(motivoManutencao).append("\n");
                    mensagem.append("urgenciaManutencao: ").append(urgenciaManutencao).append("\n");
                    mensagem.append("descricaoManutencao: ").append(descricaoManutencao).append("\n");
                    mensagem.append("valor: ").append(valor).append("\n");
                    mensagem.append("nomeTotem: ").append(nomeTotem).append("\n");
                    mensagem.append("aprovado: ").append(aprovado).append("\n");
                    mensagem.append("dataAtual: ").append(dataAtual).append("\n");
                    mensagem.append("estadoTotem: ").append(estadoTotem).append("\n");
                    mensagem.append("--------------------").append("\n");

                    enviarParaSlack(mensagem.toString());
                }
            } catch (SQLException e) {
                System.err.println("Erro ao processar resultado da consulta: " + e.getMessage());
            }
            return null;
        });
    }

    private void enviarParaSlack(String mensagem) {
        IntegracaoSlack.enviarMensagem(mensagem);
    }
}
