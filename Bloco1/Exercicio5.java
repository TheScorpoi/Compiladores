package Bloco1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exercicio5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> traducaoMap = new HashMap<>();
        List<Integer> multiplicaList = new ArrayList<>();

        try (Scanner input = new Scanner(new File("numbers.txt"))) {
            while (input.hasNextLine()) {
                String[] tab = input.nextLine().split(" - ");
                traducaoMap.put(tab[1], Integer.parseInt(tab[0]));
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // ler o input do utilizador
        String[] frase = sc.nextLine().split("[ -]");

        int num = 1;
        int inicio = 1;
        for (int i = 0; i < frase.length; i++) {
            if (traducaoMap.containsKey(frase[i])) {
                if (traducaoMap.get(frase[i]) == 1000000 || traducaoMap.get(frase[i]) == 1000|| traducaoMap.get(frase[i]) == 100) {
                    num = num * traducaoMap.get(frase[i]);
                    inicio = 0;
                } else {
                    if (inicio != 1) {
                        multiplicaList.add(num);
                    }
                    num = traducaoMap.get(frase[i]);
                    inicio = 0;
                }
            } else if (!frase[i].equals("and")) {
                System.err.printf("Palavra inserida [ %s ] nao tem correspondência\n", frase[i]);
                System.exit(1);
            }

        }
        multiplicaList.add(num);

        int soma = 0;
        for (Integer integer : multiplicaList) {
            soma += integer;
        }

        imprimirResultados(frase, soma);
        sc.close();
    }
    
    public static void imprimirResultados(String[] input2, int soma) {
        for (String string : input2) {
            System.out.printf("%s ", string);
        }
        System.out.print("-> ");
        System.out.println(soma);
    }
}
