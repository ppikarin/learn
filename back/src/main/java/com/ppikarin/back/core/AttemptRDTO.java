package com.ppikarin.back.core;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Value
public class AttemptRDTO {

    @Min(1) @Max(99)
    int factorA, factorB;
    @NotBlank
    String humanName;
    @Positive
    int guess;

}
