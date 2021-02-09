package me.study.java8to14;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;


//@SpringBootApplication
public class Java8to14Application {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

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

    ExecutorService callExecutor2 = Executors.newFixedThreadPool(2);
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

    System.out.println("START~!!");

    CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello " + Thread.currentThread().getName());
      return "hello";
    });

    CompletableFuture<String> future = hello2.thenCompose(ss -> getWorld(s));
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

  public class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2){
      return a1.getWeight().compareTo(a2.getWeight());
    }
  }

  public void LamddaTest() {

    List<Apple> inventory = new ArrayList<>();
    inventory.add(Apple.builder().weight("150").color("blue").build());
    inventory.add(Apple.builder().weight("100").color("red").build());
    inventory.add(Apple.builder().weight("180").color("green").build());

    //STEP01. 코드 전달
    inventory.sort(new AppleComparator());

    //STEP02. 익명 클래
    inventory.sort(new Comparator<Apple>() {
      public int compare(Apple o1, Apple o2) {
        return  o1.getWeight().compareTo(o2.getWeight());
      }
    });

    //STEP03. 람다표현식
    // 1) 전체
    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    // 2) 간소-1
    inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
    // 3) 간소-2
    inventory.sort(Comparator.comparing((Apple a) -> a.getWeight()));
    // 4) 간소-3
    inventory.sort(Comparator.comparing(Apple::getWeight));

    // 정렬 심화
    // 1) 역정렬
    inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

    // 2) 역정렬 + 정렬 한개 추가
    inventory.sort(Comparator.comparing(Apple::getWeight)
            .reversed() //역정렬
            .thenComparing(Apple::getCountry)); // 정렬 조건 추

  }


}
