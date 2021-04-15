package Bloco1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exercicio4 {

    public static void main(String[] args) {
        Map<String, Integer> numbersMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        try (Scanner input = new Scanner(new File("numbers.txt"));) {
            while (input.hasNext()) {
                String[] tab = input.nextLine().split(" - ");
                numbersMap.put(tab[1], Integer.parseInt(tab[0]));
            }
            List<String> fraseList = new ArrayList<>();
            List<Integer> numerosList = new ArrayList<>();
            String[] input2 = sc.nextLine().split("[ -]");
            for (int i = 0; i < input2.length; i++) {
                if (numbersMap.containsKey(input2[i])) {
                    numerosList.add(numbersMap.get(input2[i]));
                } else {
                    fraseList.add(input2[i]);
                }
            }
            fraseList.stream().distinct().forEach((s) -> System.out.printf("%s ", s));
            numerosList.stream().distinct().forEach((s) -> System.out.printf("%d ", s));
            System.out.println();
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
