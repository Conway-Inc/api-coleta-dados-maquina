package org.example;

public class Funcionario {

    private int idFuncionario;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;
    private String foto;
    private int fkGerente;
    private int fkEmpresa;

    public Funcionario(){};
    public Funcionario(int idFuncionario, String email, String senha, String nome, String cpf, String telefone, String dataNascimento, String foto, int fkGerente, int fkEmpresa) {
        this.idFuncionario = idFuncionario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
        this.fkGerente = fkGerente;
        this.fkEmpresa = fkEmpresa;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getFkGerente() {
        return fkGerente;
    }

    public void setFkGerente(int fkGerente) {
        this.fkGerente = fkGerente;
    }

    public int getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(int fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
