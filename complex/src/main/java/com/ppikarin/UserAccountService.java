package com.ppikarin;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

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
    public void createAdmin() {

        checkCreateRoles();

        UserAccount admin = new UserAccount();
        admin.setFirstName("Pavel");
        admin.setLastName("Pikarin");
        admin.setEmail("ppikarin@gmail.com");
        admin.setPassword(passwordEncoder.encode("taliesyn"));
        admin.setRoles(Arrays.asList(getRole(ROLE_ADMIN)));

        userAccountRepository.save(admin);

        UserAccount user = new UserAccount();
        user.setFirstName("Ivan");
        user.setLastName("Ivanovich");
        user.setEmail("aa@bb.cc");
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

        for (int j = 0; j < number; j++) {

            UserAccount employee = new UserAccount();
            employee.setFirstName(getString(6));
            employee.setLastName(getString(10));

            userAccountRepository.save(employee);
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
}
