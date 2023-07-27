package zad3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>(List.of(7, 1, 9, 4, 6, 8, 2, 5));
        MinMax<Integer> integerMinMax = MinMaxService.getMinAndMax(integerList);
        System.out.println("Min: " + integerMinMax.getMin());
        System.out.println("Max: " + integerMinMax.getMax());

        List<String> stringList = new ArrayList<>(List.of("jabłko", "banan", "pomarańcz", "winogronko"));
        MinMax<String> stringMinMax = MinMaxService.getMinAndMax(stringList);
        System.out.println("Min: " + stringMinMax.getMin());
        System.out.println("Max: " + stringMinMax.getMax());
    }
}

