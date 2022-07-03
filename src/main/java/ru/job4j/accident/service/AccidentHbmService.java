package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentHbm;

import java.util.List;

@Service
public class AccidentHbmService {
    private final AccidentHbm accidentHbm;

    public AccidentHbmService(AccidentHbm accidentHbm) {
        this.accidentHbm = accidentHbm;
    }

    public List<Accident> getAll() {
        return accidentHbm.getAll();
    }

    public void saveOrUpdateAccident(Accident accident, int accidentTypeId, String[] ruleIds) {
        accidentHbm.saveOrUpdateAccident(accident, accidentTypeId, ruleIds);
    }

    public List<AccidentType> getAllTypes() {
        return accidentHbm.getAllTypes();
    }

    public List<Rule> getAllRules() {
        return accidentHbm.getAllRules();
    }

    public Accident findById(int id) {
        return accidentHbm.findAccidentById(id);
    }
}
