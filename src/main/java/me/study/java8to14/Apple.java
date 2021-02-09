package me.study.java8to14;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Apple {

    String country;
    String color;
    String weight;
}
