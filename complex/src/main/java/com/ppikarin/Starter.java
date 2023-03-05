package com.ppikarin;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Starter {

    private final UserAccountService userAccountService;

    @PostConstruct
    public void postConstruct() {
        userAccountService.createAdmin();
    }
}
