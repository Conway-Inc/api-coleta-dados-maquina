package school.sptech;

public class Retangulo extends Figura {
    private Double base;
    private Double altura;

    public Retangulo(String cor, Integer espessura, Double base, Double altura) {
        super(cor, espessura);
        this.base = base;
        this.altura = altura;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    @Override
    public Double calcularArea() {
        return (base * altura);
    }

    @Override
    public String toString() {
        return "Retangulo{" +
                "cor " + getCor() +
                ", espessura " + getEspessura() +
                ", base=" + base +
                ", altura=" + altura +
                ", total area= " + calcularArea() +
                '}';
    }
}
