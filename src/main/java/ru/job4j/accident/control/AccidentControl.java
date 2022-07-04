package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentRepository;
import ru.job4j.accident.store.AccidentTypeRepository;
import ru.job4j.accident.store.RuleRepository;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentRepository accidents;
    private final AccidentTypeRepository accidentTypes;
    private final RuleRepository rules;

    public AccidentControl(AccidentRepository accidents, AccidentTypeRepository accidentTypes, RuleRepository rules) {
        this.accidents = accidents;
        this.accidentTypes = accidentTypes;
        this.rules = rules;
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute AccidentType accidentType, @ModelAttribute Rule rule) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("accidentTypes", findAllAccidentTypes());
        model.addAttribute("rules", findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                       HttpServletRequest req) {
        saveOrUpdateAccident(accident, accidentTypeId, req);
        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(Model model, @RequestParam("id") int id, @ModelAttribute AccidentType accidentType,
                             @ModelAttribute Rule rule) {
        model.addAttribute("accident", accidents.findById(id).get());
        model.addAttribute("accidentTypes", findAllAccidentTypes());
        model.addAttribute("rules", findAllRules());
        return "accident/updateAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                         HttpServletRequest req) {
        saveOrUpdateAccident(accident, accidentTypeId, req);
        return "redirect:/";
    }

    private void saveOrUpdateAccident(@ModelAttribute Accident accident, @RequestParam("accidentType.id") int accidentTypeId,
                                      HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("ruleIds");

        Arrays.stream(ruleIds)
                .map(ruleId -> rules.findById(Integer.parseInt(ruleId)).get())
                .forEach(rule -> accident.getRules().add(rule));

        accident.setAccidentType(accidentTypes.findById(accidentTypeId).get());
        accidents.save(accident);
    }

    private List<AccidentType> findAllAccidentTypes() {
        List<AccidentType> accidentTypeList = new ArrayList<>();
        accidentTypes.findAll().forEach(accidentTypeList::add);
        return accidentTypeList;
    }

    private List<Rule> findAllRules() {
        List<Rule> ruleList = new ArrayList<>();
        rules.findAll().forEach(ruleList::add);
        return ruleList;
    }
}
