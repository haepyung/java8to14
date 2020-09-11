package me.study.java8to14;

import org.springframework.cglib.core.internal.Function;

public class Plus10 implements Function<Integer, Integer> {

  @Override
  public Integer apply(Integer integer) {
    return integer + 10;
  }
}
