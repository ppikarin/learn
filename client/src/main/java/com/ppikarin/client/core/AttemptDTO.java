package com.ppikarin.client.core;

import lombok.Data;

@Data
public class AttemptDTO {

    private String human;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

}