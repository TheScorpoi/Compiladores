package Aula01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercicio6 {

    public static void main(String[] args) {

        Map<String, String> traducao = new HashMap<>();

        try (Scanner input = new Scanner(new File("dic1.txt"))) {

            while (input.hasNextLine()) {
                String[] tab = input.nextLine().split(" ");
                traducao.put(tab[0], tab[1]);
            }

            Scanner sc = new Scanner(System.in);
            String[] inputTab = sc.nextLine().split(" ");
            for (String palavra : inputTab) {
                if (traducao.containsKey(palavra)) {
                    System.out.printf("%s ", traducao.get(palavra));
                } else {
                    System.out.printf("%s ", palavra);
                }
            }
            System.out.println();

            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }
}
