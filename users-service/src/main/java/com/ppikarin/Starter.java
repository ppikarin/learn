package com.ppikarin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Starter {

    private final UserAccountService userAccountService;

    @PostConstruct
    public void postConstruct() {
        userAccountService.createUsers();
    }
}
