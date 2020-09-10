package me.study.java8to14;

@FunctionalInterface
public interface RunSomething {
  int doIt(int num);

  static void printName() {
    System.out.println("haepyung");
  }

  default void printAge() {
    System.out.println("33");
  }
}
