package com.mumtaz.learn.reactive.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Event {
    String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date date;
}
