/**
 * Используя Reflection API, напишите программу, которая
 * выводит на экран все методы класса String.
 */

import java.lang.reflect.Method;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personalClass = Class.forName("java.lang.String");
        Method[] methods = personalClass.getDeclaredMethods();
        for (Method method : methods){
            System.out.println(method);
        }
    }
}