package Bloco1;

import java.util.Scanner;


public class Exercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double numero1 = sc.nextDouble();
        String operador = sc.next();
        double numero2 = sc.nextDouble();

        resul(numero1, numero2, operador);
        sc.close();
    }

    public static double resul(double numero1, double numero2, String operador) {
        double resultado = 0;   
        switch (operador) {
        case "+":
            resultado = numero1 + numero2;
            System.out.printf("%.1f %s %.1f = %.1f\n", numero1, operador, numero2, resultado);
            break;
        case "-":
            resultado = numero1 - numero2;
            System.out.printf("%.1f %s %.1f = %.1f\n", numero1, operador, numero2, resultado);
            break;
        case "*":
            resultado = numero1 * numero2;
            System.out.printf("%.1f %s %.1f = %.1f\n", numero1, operador, numero2, resultado);
            break;
        case "/":
            resultado = numero1 / numero2;
            System.out.printf("%.1f %s %.1f = %.1f\n", numero1, operador, numero2, resultado);
            break;
        default:
            System.err.println("O operador introduzido é inválido");
            System.exit(1);
            break;
        }
        return resultado;
    }
}