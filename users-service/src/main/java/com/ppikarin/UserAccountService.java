package com.ppikarin;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class UserAccountService {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    public static final String INCORRECT_INPUT = "Incorrect input";
    private String dict = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random();

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void createUsers() {

        checkCreateRoles();

        UserAccount admin = new UserAccount();
        admin.setFirstName("Pavel");
        admin.setLastName("Pikarin");
        admin.setEmail("admin@mail.com");
        admin.setPassword(passwordEncoder.encode("111"));
        admin.setRoles(Arrays.asList(getRole(ROLE_ADMIN)));

        userAccountRepository.save(admin);

        UserAccount user = new UserAccount();
        user.setFirstName("Ivan");
        user.setLastName("Ivanovich");
        user.setEmail("user@mail.com");
        user.setPassword(passwordEncoder.encode("111"));
        user.setRoles(Arrays.asList(getRole(ROLE_USER)));

        userAccountRepository.save(user);
    }

    private void checkCreateRoles() {
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> allPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);

        createRoleIfNotFound(ROLE_ADMIN, allPrivileges);
        createRoleIfNotFound(ROLE_USER, Arrays.asList(readPrivilege));
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Optional<Privilege> privilegeOpt = privilegeRepository.findByName(name);

        if (privilegeOpt.isEmpty()) {
            Privilege privilege = new Privilege(name);
            privilegeRepository.save(privilege);
            return privilege;
        }
        return privilegeOpt.get();
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Optional<Role> roleOpt = roleRepository.findByName(name);
        if (roleOpt.isEmpty()) {
            Role role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
            return role;
        }
        return roleOpt.get();
    }

    private Role getRole(String name) {
        Role role = roleRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException("role not found: " + name));
        return role;
    }

    public String getDict() {
        return dict;
    }

    public void fill(int number) throws Exception {

        List<Role> roles = Arrays.asList(getRole(ROLE_USER));

        for (int j = 0; j < number; j++) {

            UserAccount user = new UserAccount();
            user.setFirstName(getString(1).toUpperCase()+getString(7));
            user.setLastName(getString(1).toUpperCase()+getString(9));
            user.setEmail(getString(5) + "@" + getString(2) + "." + getString(2));
            user.setRoles(roles);

            userAccountRepository.save(user);
        }
    }

    public String getString(int length) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(getChar());
        }
        return sb.toString();
    }

    public String getChar() throws Exception {
        int pos = random.nextInt(dict.length());
        return getSubstring(pos);
    }

    public String getSubstring(int pos) throws Exception {
        if (pos > dict.length() - 1) {
            throw new Exception(INCORRECT_INPUT);
        }
        return dict.substring(pos, pos + 1);
    }

    public List<UserAccountDTO> get() {
        return StreamSupport
                .stream(userAccountRepository.findAll().spliterator(), false)
                .map(ua -> new UserAccountDTO(ua.getId(), ua.getFirstName(), ua.getLastName(), ua.getEmail(), null))
                .collect(Collectors.toList());
    }
}
