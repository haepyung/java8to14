package me.study.java8to14;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;


//@SpringBootApplication
public class Java8to14Application {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    //SpringApplication.run(Java8to14Application.class, args);

    /*
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

    List<String> names = new ArrayList<>();
    names.add("haepyung");
    names.add("whitheship");
    names.add("foo");

    //for 문 방법1
    names.forEach(System.out::println);
    //for 문 방법2
    for (String n : names)
      System.out.println(n);

    Spliterator<String> spliterator = names.spliterator();
    Spliterator<String> spliterator1 = spliterator.trySplit();
    while (spliterator.tryAdvance(System.out::println));
    while (spliterator1.tryAdvance(System.out::println));
    long h = names.stream().map(String::toUpperCase).filter(s -> s.startsWith("H")).count();
    System.out.println(h);
    System.out.println("==============================");
    /*
    Thread thread = new Thread(() -> {
      while (true) {
        System.out.println("Thread:: " + Thread.currentThread().getName());
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          System.out.println("Exit!!");
          return;
        }
      }
    });
    thread.start();
    Thread.sleep(3000L);
    thread.interrupt();

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.submit(getRunnable("start"));
    executorService.shutdown();

    ExecutorService executorService2 = Executors.newFixedThreadPool(2);
    executorService2.submit(getRunnable("hello"));
    executorService2.submit(getRunnable("work"));
    executorService2.submit(getRunnable("park"));
    executorService2.submit(getRunnable("hae"));
    executorService2.shutdown();

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService.schedule(getRunnable("schedule"), 5, TimeUnit.SECONDS);
    scheduledExecutorService.shutdown();


    //callable
    ExecutorService callExecutor = Executors.newSingleThreadExecutor();
    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };
    Future<String> submit = callExecutor.submit(hello);
    System.out.println("Started!!");
    submit.get();
    System.out.println("END!!");

    ExecutorService callExecutor = Executors.newFixedThreadPool(2);
    Callable<String> haepyung = () -> {
      Thread.sleep(2000L);
      return "Haepyung";
    };

    Callable<String> java = () -> {
      Thread.sleep(1000L);
      return "Java";
    };

    List<Future<String>> futures = callExecutor.invokeAll(Arrays.asList(haepyung, java));
    for (Future<String> f : futures) {
      System.out.println(f.get());
    }

    String s = callExecutor.invokeAny(Arrays.asList(haepyung, java));
    System.out.println(s);

    callExecutor.shutdown();
    */

    System.out.println("START~!!");

    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello " + Thread.currentThread().getName());
      return "hello";
    });

    CompletableFuture<String> future = hello.thenCompose(s -> getWorld(s));
    System.out.println(future.get());


  }

  private static CompletableFuture<String> getWorld(String msg) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("World " + Thread.currentThread().getName());
      return msg.toUpperCase() + " World";
    });
  }

  private static Runnable getRunnable(String msg) {
    return () -> {
      System.out.println(msg + " Executor Thread:: " + Thread.currentThread().getName());
    };
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
