package Bloco1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exercicio6C {

    public static void main(String[] args) {
        Map<String, String> traducaoMap = new HashMap<>();
        List<String> palavrasList = new ArrayList<>();
        List<String> resultadosList = new ArrayList<>();

        try (Scanner input = new Scanner(new File("dic2.txt"))) {
            while (input.hasNextLine()) {
                String[] tab = input.nextLine().split(" ");
                String definicao = tab[1];
                if (tab.length > 2) {
                    for (int i = 2; i < tab.length; i++) {
                        definicao += " " + tab[i];
                    }
                }
                traducaoMap.put(tab[0], definicao);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        String tab  = "";
        try (Scanner input = new Scanner(new File("texto2.txt"))) {
            while (input.hasNextLine()) {
                tab = input.nextLine();
                
            }   

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        resultadosList = recursiveFunction(traducaoMap, tab, palavrasList);

        for (String string : resultadosList) {
            System.out.printf("%s ", string);
        }
        System.out.println();
    }

    public static List<String> recursiveFunction(Map<String, String> traducoesMap, String palavras, List<String> palavra) {

        for (String word : palavras.split(" ")) {
            if (traducoesMap.containsKey(word)) {
                recursiveFunction(traducoesMap,traducoesMap.get(word) , palavra);
            } else if (!word.equals("")) {
                palavra.add(word);
            }
        }
        return palavra;
    }
}
