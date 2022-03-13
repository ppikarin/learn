package com.ppikarin.back.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Human(final String name) {
        this(null, name);
    }
}
