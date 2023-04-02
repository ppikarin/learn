package com.ppikarin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAccountDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}