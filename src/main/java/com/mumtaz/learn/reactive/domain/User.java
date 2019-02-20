package com.mumtaz.learn.reactive.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document (collection = "user")
@ToString
public class User {

    String id;
    String firstName;

    public User() {
    }

    public User(String id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }
}
