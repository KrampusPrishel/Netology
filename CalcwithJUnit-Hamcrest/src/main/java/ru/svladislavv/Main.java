package ru.svladislavv;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c = calc.devide.apply(a, b - 1);

        calc.println.accept(c);
        calc.println.accept(calc.abs.apply(-2));
    }
}