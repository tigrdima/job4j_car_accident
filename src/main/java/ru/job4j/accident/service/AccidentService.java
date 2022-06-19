package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentMemStore;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMemStore accidentMemStore;

    public AccidentService(AccidentMemStore accidentMemStore) {
        this.accidentMemStore = accidentMemStore;
    }

    public Collection<Accident> getAll() {
        return accidentMemStore.getAll();
    }

    public void save(Accident accident, int accidentTypeId, String[] ruleIds) {
        accidentMemStore.save(accident, accidentTypeId, ruleIds);
    }

    public Accident findById(int id) {
        return accidentMemStore.fidById(id);
    }

    public void update(Accident accident, int accidentTypeId, String[] ruleIds) {
        accidentMemStore.update(accident, accidentTypeId, ruleIds);
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentMemStore.getAllTypes();
    }

    public AccidentType findByIdType(int id) {
        return accidentMemStore.findByIdType(id);
    }

    public Rule findByIdRule(int id) {
        return accidentMemStore.findByIdRule(id);
    }

    public Set<Rule> getAllRules() {
        return accidentMemStore.getAllRules();
    }
}
