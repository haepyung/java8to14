package me.study.java8to14;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
public class Java8to14Application {

  public static void main(String[] args) {
    //SpringApplication.run(Java8to14Application.class, args);

    Function<Integer, Integer> plus11 = (i) -> i + 11;
    RunSomething runSomething11 = (num) -> { return num + 11; };
    System.out.println(plus11.apply(1));
    System.out.println(runSomething11.doIt(1));

    Plus10 plus10 = new Plus10();
    RunSomething runSomething = num -> num + 10;;
    System.out.println(plus10.apply(1));
    System.out.println(runSomething.doIt(1));


    Function<Integer, Integer> multiply2 = (i) -> i * 2;
    Function<Integer, Integer> compose = plus11.compose(multiply2);
    Function<Integer, Integer> andThen = plus11.andThen(multiply2);
    System.out.println(compose.apply(2));
    System.out.println(andThen.apply(2));

    Supplier<Integer> get10 = () -> 10;

    BinaryOperator<Integer> sum = Integer::sum;
    BinaryOperator<Integer> sum2 = (a, b) -> a + b;
  }


  private void run() {

    final int baseNumber = 10;

    //로컬 클래
    class LocalClass {
      void printBaseNumber() {
        System.out.println(baseNumber);
      }
    }

    //익명 클래스
    Consumer<Integer> integerConsumer = new Consumer<Integer>() {
      @Override
      public void accept(Integer integer) {
        System.out.println(baseNumber);
      }
    };

    //람다
    IntConsumer printInt = (i) -> {
      System.out.println(i + baseNumber);
    };
  }

}
