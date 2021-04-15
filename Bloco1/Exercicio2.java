package Bloco1;

import java.util.HashMap;
import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) {

        HashMap<String, Double> mapa = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String numero1 = sc.next();
            String operador = sc.next();
            String numero2 = sc.next();
            double resultado = 0;
            numero2 = numero2.trim();

            switch (operador) {
            case "=":
                if (numero2.contains("+") || numero2.contains("-") || numero2.contains("*") || numero2.contains("/")) {
                    String[] caracter = numero2.trim().split("\\s+");

                    if (mapa.containsKey(caracter[0]) && !mapa.containsKey(caracter[2])) {
                        resultado = calcular(mapa.get(caracter[0]), caracter[1], Double.valueOf(caracter[2]));
                        mapa.put(numero1, resultado);

                    } else if (mapa.containsKey(caracter[2]) && !mapa.containsKey(caracter[0])) {
                        resultado = calcular(Double.valueOf(caracter[0]), caracter[1], mapa.get(caracter[2]));
                        mapa.put(numero1, resultado);

                    } else if (mapa.containsKey(caracter[0]) && mapa.containsKey(caracter[2])) {
                        resultado = calcular(mapa.get(caracter[0]), caracter[1], mapa.get(caracter[2]));
                        mapa.put(numero1, resultado);
                    } else {
                        resultado = calcular(Double.valueOf(caracter[0]), caracter[1], Double.valueOf(caracter[2]));
                        mapa.put(numero1, resultado);
                    }
                } else {
                    mapa.put(numero1, Double.valueOf(numero2));
                    resultado = Double.valueOf(numero2);
                }
                break;

            default:
                if (mapa.containsKey(numero1) && !mapa.containsKey(numero2)) {
                    resultado = calcular(mapa.get(numero1), operador, Double.valueOf(numero2));

                } else if (mapa.containsKey(numero2) && !mapa.containsKey(numero1)) {
                    resultado = calcular(Double.valueOf(numero1), operador, mapa.get(numero2));

                } else if (mapa.containsKey(numero2) && mapa.containsKey(numero1)) {
                    resultado = calcular(mapa.get(numero1), operador, mapa.get(numero2));
                } else {
                    resultado = calcular(Double.valueOf(numero1), operador, Double.valueOf(numero2));
                }
            }
            System.out.println("resultado = " + resultado);
        }
        sc.close();
    }

    public static double calcular(double numero1, String operador, double numero2) {

        double resultado = 0;
        switch(operador) {
            case "+":
                resultado = numero1 + numero2;
                break;

            case "-":
                resultado = numero1 - numero2;
                break;

            case "*":
                resultado = numero1 * numero2;
                break;

            case "/":
                resultado = numero1 / numero2;
                break;

            default:
                System.err.println("O operador introduzido é inválido");
                System.exit(1);
        }

        return resultado;
    }
}
