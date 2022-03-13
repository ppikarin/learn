package com.ppikarin.back.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class AttemptDTO {

    private String human;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

}