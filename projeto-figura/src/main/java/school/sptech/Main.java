package school.sptech;

public class Main {
    public static void main(String[] args) {

        Imagem i1 = new Imagem();

        Retangulo r1 = new Retangulo("roxo", 3, 23.0, 32.0);
        Quadrado q1 = new Quadrado("azul", 2, 2.0);
        Circulo c1 = new Circulo("rosa", 2, 2.00);
        Triangulo t1 = new Triangulo("vermelho", 2, 3.0, 4.0);

        i1.adicionarFigura(r1);
        i1.adicionarFigura(q1);
        i1.adicionarFigura(c1);
        i1.adicionarFigura(t1);
        System.out.println("Exibindo as figuras: ");
        i1.exibeFiguras();

        System.out.println("-".repeat(60));
        i1.exibeSomaArea();
        System.out.println("-".repeat(60));
        System.out.println("Áreas que são maiores que 20: ");
        i1.exibeFiguraAreaMaior20();
        System.out.println("-".repeat(60));
        System.out.println("Exibição dos quadrados: ");
        i1.exibeQuadrado();



    }
}