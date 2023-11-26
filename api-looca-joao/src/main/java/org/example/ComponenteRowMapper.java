package org.example;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponenteRowMapper implements RowMapper<Componente> {

    @Override
    public Componente mapRow(ResultSet resultSet, int i) throws SQLException {

        String nome = resultSet.getString("nome");

        if (nome.equals("CPU")) {
            Componente cpu = new Cpu(
                    resultSet.getInt("idComponente"),
                    resultSet.getString("nome"),
                    resultSet.getString("unidadeMedida"),
                    100
            );

            return cpu;

        } else if (nome.equals("Memória")) {
            Componente memoria = new Memoria(
                    resultSet.getInt("idComponente"),
                    resultSet.getString("nome"),
                    resultSet.getString("unidadeMedida")
            );

            return memoria;

        } else {

            Componente disco = new Disco(
                    resultSet.getInt("idComponente"),
                    resultSet.getString("nome"),
                    resultSet.getString("unidadeMedida")
            );

            return disco;
        }

    }
}
