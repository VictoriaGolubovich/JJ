/*
Напишите программу, которая использует Stream API для обработки списка
чисел. Программа должна вывести на экран среднее значение всех четных
чисел в списке.
 */

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(4, 6, 19, 3, 7, 35, 8, 4);

        System.out.println(numbers.stream().filter(num -> num%2 == 0).mapToDouble(num -> num).average());


    }
}