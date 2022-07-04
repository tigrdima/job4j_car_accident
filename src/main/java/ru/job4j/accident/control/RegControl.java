package ru.job4j.accident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.store.AuthorityRepository;
import ru.job4j.accident.store.UserRepository;

@Controller
public class RegControl {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder passwordEncoder, UserRepository users, AuthorityRepository authorities) {
        this.passwordEncoder = passwordEncoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/regUser")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER").get());
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/formRegUser")
    public String regPage() {
        return "formRegUser";
    }
}
