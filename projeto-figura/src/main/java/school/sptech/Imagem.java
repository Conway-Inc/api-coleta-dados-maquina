package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Imagem {

    List<Figura> figura;

    public Imagem() {
        this.figura = new ArrayList<>();
    }

    public void adicionarFigura (Figura f) {
        if (f != null) {
            figura.add(f);
        } else {
            System.out.println("Houve um erro ao adicionar a figura");
        }
    }

    public void exibeFiguras () {
        for (Figura figura1 : figura) {
            System.out.println(figura1);
        }
    }

    public void exibeSomaArea () {
        Double totalArea = 0.00;
        for (Figura figura1 : figura) {
            totalArea += figura1.calcularArea();
        }
        System.out.println(String.format("O total da área é de %.2f", totalArea));
    }

    public void exibeFiguraAreaMaior20 () {
        Double totalAreaMaiorque20 = 0.00;
        for (Figura figura1 : figura) {
           if (figura1.calcularArea() > 20) {
               System.out.println("A área da figura " + figura1 + " é maior que 20");
           }
        }
    }

    public void exibeQuadrado () {
        for (Figura figura1 : figura) {
            if (figura1 instanceof Quadrado) {
                System.out.println(figura1);
            }
        }
    }
}
