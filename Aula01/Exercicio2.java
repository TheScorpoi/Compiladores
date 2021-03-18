package Aula01;

import java.util.HashMap;
import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) {

        HashMap<String, Double> variaveis = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String op1 = sc.next();
            String operador = sc.next();
            String op2 = sc.nextLine();
            double result = 0;
            op2 = op2.trim();

            switch (operador) {
            case "=":
                if (op2.contains("+") || op2.contains("-") || op2.contains("*") || op2.contains("/")) {
                    String[] op = op2.trim().split("\\s+");

                    if (variaveis.containsKey(op[0]) && !variaveis.containsKey(op[2])) {
                        result = calcular(variaveis.get(op[0]), op[1], Double.valueOf(op[2]));
                        variaveis.put(op1, result);

                    } else if (variaveis.containsKey(op[2]) && !variaveis.containsKey(op[0])) {
                        result = calcular(Double.valueOf(op[0]), op[1], variaveis.get(op[2]));
                        variaveis.put(op1, result);

                    } else if (variaveis.containsKey(op[0]) && variaveis.containsKey(op[2])) {
                        result = calcular(variaveis.get(op[0]), op[1], variaveis.get(op[2]));
                        variaveis.put(op1, result);
                    } else {
                        result = calcular(Double.valueOf(op[0]), op[1], Double.valueOf(op[2]));
                        variaveis.put(op1, result);
                    }
                } else {
                    variaveis.put(op1, Double.valueOf(op2));
                    result = Double.valueOf(op2);
                }
                break;

            default:
                if (variaveis.containsKey(op1) && !variaveis.containsKey(op2)) {
                    result = calcular(variaveis.get(op1), operador, Double.valueOf(op2));

                } else if (variaveis.containsKey(op2) && !variaveis.containsKey(op1)) {
                    result = calcular(Double.valueOf(op1), operador, variaveis.get(op2));

                } else if (variaveis.containsKey(op2) && variaveis.containsKey(op1)) {
                    result = calcular(variaveis.get(op1), operador, variaveis.get(op2));
                } else {
                    result = calcular(Double.valueOf(op1), operador, Double.valueOf(op2));
                }
            }

            System.out.println("result = " + result);
        }

        sc.close();
    }


    public static double calcular(double op1, String operador, double op2) {

        double result = 0;
        switch(operador) {
            case "+":
                result = op1 + op2;
                break;

            case "-":
                result = op1 - op2;
                break;

            case "*":
                result = op1 * op2;
                break;

            case "/":
                result = op1 / op2;
                break;

            default:
                System.err.println("Erro: Operação inválida");
        }

        return result;
    }
}
