package org.example;

public class Alerta {

    private Integer id;
    private Integer tipo;
    private String descricao;
    private Integer fkRegistro;

    public Alerta(){};

    public Alerta(Integer id, Integer tipo, String descricao, Integer fkRegistro) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.fkRegistro = fkRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getFkRegistro() {
        return fkRegistro;
    }

    public void setFkRegistro(Integer fkRegistro) {
        this.fkRegistro = fkRegistro;
    }

    public static class Totem {

        private int idTotem;
        private String nome;
        private int fkAeroporto;
        private int fkEmpresa;

        public Totem(){};

        public Totem(int idTotem, String nome, int fkAeroporto, int fkEmpresa) {
            this.idTotem = idTotem;
            this.nome = nome;
            this.fkAeroporto = fkAeroporto;
            this.fkEmpresa = fkEmpresa;
        }

        public int getIdTotem() {
            return idTotem;
        }

        public void setIdTotem(int idTotem) {
            this.idTotem = idTotem;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getFkAeroporto() {
            return fkAeroporto;
        }

        public void setFkAeroporto(int fkAeroporto) {
            this.fkAeroporto = fkAeroporto;
        }

        public int getFkEmpresa() {
            return fkEmpresa;
        }

        public void setFkEmpresa(int fkEmpresa) {
            this.fkEmpresa = fkEmpresa;
        }

        @Override
        public String toString() {
            return "Totem{" +
                    "idTotem=" + idTotem +
                    ", nome='" + nome + '\'' +
                    ", fkAeroporto=" + fkAeroporto +
                    ", fkEmpresa=" + fkEmpresa +
                    '}';
        }
    }
}
