package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentHbmService;

@Controller
public class IndexControl {
    private final AccidentHbmService accidents;

    public IndexControl(AccidentHbmService accidentHbmService) {
        this.accidents = accidentHbmService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.getAll());
        return "index";
    }
}
