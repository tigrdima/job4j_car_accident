package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute AccidentType accidentType, @ModelAttribute Rule rule) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("accidentTypes", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                       HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("ruleIds");
        accidentService.save(accident, accidentTypeId, ruleIds);

        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(Model model, @RequestParam("id") int id, @ModelAttribute AccidentType accidentType,
                             @ModelAttribute Rule rule) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("accidentTypes", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/updateAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                         HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("ruleIds");
        accidentService.update(accident, accidentTypeId, ruleIds);
        return "redirect:/";
    }

}
