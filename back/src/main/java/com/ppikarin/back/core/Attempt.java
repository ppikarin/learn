package com.ppikarin.back.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "eg-attempt-human",
        attributeNodes = {
                @NamedAttributeNode(value = "human"),
        }
)
public class Attempt {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id")
    private Human human;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

}