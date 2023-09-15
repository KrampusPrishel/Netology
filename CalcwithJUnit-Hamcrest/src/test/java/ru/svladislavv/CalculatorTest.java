package ru.svladislavv;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CalculatorTest {

    @BeforeAll
    static void initSuite() {
        System.out.println("Running CalculatorTest:");
    }

    @AfterAll
    static void completeSuite() {
        System.out.println("CalculatorTest complete!!!");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Starting new test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Complete!");
    }

    @Test
    void plus() {
        Calculator calculator = Calculator.instance.get();
        int sumTest = calculator.plus.apply(10, -45);
//        Assertions.assertEquals(-35, sumTest);
        assertThat(-35, equalTo(sumTest));
    }

    @Test
    void minus() {
        Calculator calculator = Calculator.instance.get();
        int diffTest = calculator.minus.apply(-500, -500);
//        Assertions.assertEquals(0, diffTest);
        assertThat(0, equalTo(diffTest));
    }

    @Test
    void multiply() {
        Calculator calculator = Calculator.instance.get();
        int multTest = calculator.multiply.apply(-5, 2);
//        Assertions.assertEquals(-10, multTest);
        assertThat(-10, equalTo(multTest));
    }

    @Test
    void devide() {
        Calculator calculator = Calculator.instance.get();
        int devTest = calculator.devide.apply(13, 3);
//        Assertions.assertEquals(4, devTest);
        assertThat(4, equalTo(devTest));
    }

    @Test
    void divideByZero_checkException() throws Exception {
        Calculator calculator = Calculator.instance.get();
        Assertions.assertThrows(ArithmeticException.class, () ->
                calculator.devide.apply(5, 0));
//        assertThat(ArithmeticException.class,
//                is(calculator.devide.apply(5, 0)));
    }

    @Test
    void pow() {
        Calculator calculator = Calculator.instance.get();
        int powTest = calculator.pow.apply(12);
//        Assertions.assertEquals(144, powTest);
        assertThat(144, equalTo(powTest));
    }

    @Test
    void abs_oddNumber() {
        Calculator calculator = Calculator.instance.get();
        int absTest = calculator.abs.apply(-999);
//        Assertions.assertEquals(999, absTest);
        assertThat(999, equalTo(absTest));
    }

    @Test
    void isPositive_oddNumber() {
        Calculator calculator = Calculator.instance.get();
//        Assertions.assertFalse(calculator.isPositive.test(-30));
        assertThat(false, is(calculator.isPositive.test(-30)));
    }

}