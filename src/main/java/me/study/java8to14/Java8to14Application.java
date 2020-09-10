package me.study.java8to14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java8to14Application {

  public static void main(String[] args) {
    SpringApplication.run(Java8to14Application.class, args);

    //RunSomething runSomething = (num) -> { return num + 10; };
    RunSomething runSomething = num -> num + 10;;

    System.out.println(runSomething.doIt(1));
    System.out.println(runSomething.doIt(1));
    System.out.println(runSomething.doIt(1));

    System.out.println("java 8 to 14 study start~!!");
  }

}
