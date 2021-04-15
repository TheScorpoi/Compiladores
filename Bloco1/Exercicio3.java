package Bloco1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Exercicio3 {
    public static void main(String[] args) {
        System.out.println("Reverse Polish Notation");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String[] valores = input.split(" ");

        Stack<Double> stack = new Stack<>();
        List<String> operador = new ArrayList<>();

        for (int i = 0; i < valores.length; i++) {
            if (valores[i].matches("[0-9]+")) {
                stack.push(Double.parseDouble(valores[i]));
            } else {
                operador.add(valores[i]);
            }
        }
        System.out.println(Arrays.toString(stack.toArray()));

        if (stack.size() < 2) {
            System.err.println("ERRO: menos de 2 elementos na PILHA!");
            System.exit(1);
        }

        double resultado = 0, numero1, numero2;
        for (int i = 0; i < operador.size(); i++) {
        	numero1 = stack.peek();stack.pop();
               numero2 = stack.peek();stack.pop();
            switch (operador.get(i)) {
            case "+": 
                resultado = numero1 + numero2;
                stack.push(resultado);
                System.out.println(Arrays.toString(stack.toArray()));
                break;
            case "-":
                resultado = numero1 - numero2;
                stack.push(resultado);
                System.out.println(Arrays.toString(stack.toArray()));
                break;
            case "*":
                resultado = numero1 * numero2;
                stack.push(resultado);
                System.out.println(Arrays.toString(stack.toArray()));
                break;
            case "/":
                resultado = numero1 / numero2;
                stack.push(resultado);
                System.out.println(Arrays.toString(stack.toArray()));
                break;
            default:
                System.err.println("Operação inválida!");
                System.exit(1);
            }
        }
        sc.close();
    }
}
