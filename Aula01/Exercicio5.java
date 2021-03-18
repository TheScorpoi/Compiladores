package Aula01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercicio5 {

    public static void main(String[] args) {
        Map<String, Integer> numbersMap1 = new HashMap<>();
        Map<String, Integer> numbersMap2 = new HashMap<>();
        Map<String, Integer> numbersMap3 = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        try (Scanner input = new Scanner(new File("numbers.txt"));) {
            int counter = 0;
            while (input.hasNext()) {
                String[] tab = input.nextLine().split(" - ");
                if (counter < 21) {
                    numbersMap1.put(tab[1], Integer.parseInt(tab[0]));
                    counter++;
                } else if (counter < 29) {
                    numbersMap2.put(tab[1], Integer.parseInt(tab[0]));
                    counter++;
                } else {
                    numbersMap3.put(tab[1], Integer.parseInt(tab[0]));
                    counter++;
                }
            }

            String[] input2 = sc.nextLine().split("[ -]");
            int soma = 0, previous = 0;
            Integer tmp = null;

            for (String string : input2) {
                if (numbersMap1.get(string) != null) {
                    tmp = numbersMap1.get(string);
                    soma += tmp;
                    previous += tmp;
                } else if (numbersMap3.get(string) != null) {
                    if (soma != 0) {
                        soma -= previous;
                    }
                    soma += previous * numbersMap3.get(string);
                    tmp = null;
                    previous = 0;
                } else if (numbersMap3.get(string) != null) {
                    tmp = numbersMap3.get(string);
                    soma += tmp;
                    previous = tmp;
                }
            }
            imprimirResultados(input2, soma);
            sc.close();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void imprimirResultados(String[] input2, int soma) {
        for (String string : input2) {
            System.out.printf("%s ", string);
        }
        System.out.print("-> ");
        System.out.println(soma);
    }
}
