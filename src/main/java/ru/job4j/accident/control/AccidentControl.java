package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentHbmService;


import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentHbmService accidentsService;

    public AccidentControl(AccidentHbmService accidentsService) {
        this.accidentsService = accidentsService;
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute AccidentType accidentType, @ModelAttribute Rule rule) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("accidentTypes", accidentsService.getAllTypes());
        model.addAttribute("rules", accidentsService.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                       HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("ruleIds");
        accidentsService.save(accident, accidentTypeId, ruleIds);

        return "redirect:/";
    }

  /**  @GetMapping("/formUpdate")
    public String formUpdate(Model model, @RequestParam("id") int id, @ModelAttribute AccidentType accidentType,
                             @ModelAttribute Rule rule) {
        model.addAttribute("accident", accidentsService.findById(id));
        model.addAttribute("accidentTypes", accidentsService.getAllTypes());
        model.addAttribute("rules", accidentsService.getAllRules());
        return "accident/updateAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                         HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("ruleIds");
        accidentsService.update(accident, accidentTypeId, ruleIds);
        return "redirect:/";
    }*/

}
