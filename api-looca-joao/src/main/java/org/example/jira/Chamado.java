package org.example.jira;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class Chamado {

    private String titulo;
    private String descricao;

    public Chamado(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int abrirChamado(CredencialJira conexao){

        String jsonChamado = String.format("{\n" +
                "            \"fields\":{\n" +
                "                \"summary\": \"%s\",\n" +
                "                \"project\":{\"key\":\"%s\"},\n" +
                "                \"issuetype\": {\"name\": \"General request\"},\n" +
                "                \"description\": {\"content\": [{\"content\": [\n" +
                "                                            {\n" +
                "                                                \"text\": \"%s\",\n" +
                "                                                \"type\": \"text\"\n" +
                "                                            }],\n" +
                "                                            \"type\": \"paragraph\"}],\n" +
                "                                            \"type\": \"doc\",\n" +
                "                                            \"version\": 1}\n" +
                "            }\n" +
                "        }", this.titulo, conexao.getProjectKey(), this.descricao);

        // Codificar as credenciais para autenticação básica
        String credenciais = Base64.getEncoder().encodeToString((conexao.getUsuario() + ":" + conexao.getSenha()).getBytes());

        // Configurar o cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // Configurar a requisição HTTP
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(conexao.getUrl()))
                .header("Authorization", "Basic " + credenciais)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonChamado))
                .build();

        // Enviar a requisição e obter a resposta
        try {
            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            // Exibir a resposta
            System.out.println("Código de resposta: " + resposta.statusCode());
            System.out.println("Corpo da resposta: " + resposta.body());

            return resposta.statusCode();

        } catch (Exception e) {
            e.printStackTrace();

            return 404;

        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}