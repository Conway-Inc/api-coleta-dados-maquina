package org.example.jira;


public class CredencialJira {

    private String url;
    private String usuario;
    private String senha;
    private String projectKey;

    public CredencialJira(String url, String usuario, String senha, String projectKey) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
        this.projectKey = projectKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }
}
