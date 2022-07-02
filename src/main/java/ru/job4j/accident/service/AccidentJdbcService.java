package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
@Service
public class AccidentJdbcService {
    private final AccidentJdbc accidentJdbc;

    public AccidentJdbcService(AccidentJdbc accidentJdbc) {
        this.accidentJdbc = accidentJdbc;
    }

    public void save(Accident accident, int accidentType, String[] ruleIds) {
        accidentJdbc.save(accident, accidentType, ruleIds);
    }

    public List<Accident> getAll() {
        return accidentJdbc.getAll();
    }

    public List<AccidentType> getAllTypes() {
        return accidentJdbc.getAllTypes();
    }

    public List<Rule> getAllRules() {
        return accidentJdbc.getAllRules();
    }

    public Accident findById(int id) {
        return accidentJdbc.findById(id);
    }

    public void update(Accident accident, int accidentTypeId, String[] ruleIds) {
        accidentJdbc.update(accident, accidentTypeId, ruleIds);
    }
} */
