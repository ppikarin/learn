package com.ppikarin;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.ppikarin.UserAccountService.ROLE_ADMIN;

@Controller
@RequiredArgsConstructor
public class MainController {

    public static final String NOAUTH_TEST_PATH = "/noAuth";
    private final Starter starter;
    private final UserAccountService userAccountService;

    @GetMapping(NOAUTH_TEST_PATH)
    public @ResponseBody String noAuth(String input) throws Exception {
        return "noauth, " + input;
    }

    @GetMapping("/checkAuth")
    public @ResponseBody String checkAuth(String input) throws Exception {
        return "checkauth, " + input;
    }

    @PreAuthorize("hasRole('" + ROLE_ADMIN + "')")
    @GetMapping("/checkAdmin")
    public @ResponseBody String checkAdmin(String input) throws Exception {
        return "checkauth, " + input;
    }

    @PreAuthorize("hasRole('" + ROLE_ADMIN + "')")
    @PostMapping("/checkPostAdmin")
    public @ResponseBody String checkPostAdmin(@RequestBody UserAccountDTO userAccountDTO) throws Exception {
        return userAccountDTO.toString();
    }

    @PreAuthorize("hasRole('" + ROLE_ADMIN + "')")
    @GetMapping("/fill")
    public @ResponseBody String fill(String input) throws Exception {
        userAccountService.fill(10);
        return "Hello, " + input;
    }

}
