package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentJdbcService;

@Controller
public class IndexControl {
    private final AccidentJdbcService accidentJdbcService;

    public IndexControl(AccidentJdbcService accidentJdbcService) {
        this.accidentJdbcService = accidentJdbcService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentJdbcService.getAll());
        return "index";
    }
}
