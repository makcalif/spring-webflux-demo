package com.mumtaz.learn.reactive.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class Coffee {
    @Id
    private Long id;
    @NonNull
    private String name;
}