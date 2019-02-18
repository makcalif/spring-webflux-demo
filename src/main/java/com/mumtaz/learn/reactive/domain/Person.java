package com.mumtaz.learn.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    String name;
    Address address;
}
