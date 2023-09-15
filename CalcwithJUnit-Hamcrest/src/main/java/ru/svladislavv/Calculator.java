package ru.svladislavv;

import java.util.function.*;

public class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    BinaryOperator<Integer> plus = Integer::sum;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> devide = (x, y) -> {
//        try {
//            return x / y;
//        } catch (ArithmeticException exception) {
//            System.out.println(exception.getMessage());
//        }
//        return x / y;
//    };
        if (y == 0) throw new ArithmeticException("Деление на 0 ЗАПРЕЩЕНО!");
        else return x / y;
    };
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : -x;
    Predicate<Integer> isPositive = x -> x > 0;
    Consumer<Integer> println = System.out::println;
}