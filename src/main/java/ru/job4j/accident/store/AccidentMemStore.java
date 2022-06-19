package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class AccidentMemStore {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final List<Rule> rules = new ArrayList<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMemStore() {
        accidentTypes.put(1, new AccidentType(1, "Две машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));

        rules.add(new Rule(1, "Статья 1"));
        rules.add(new Rule(2, "Статья 2"));
        rules.add(new Rule(3, "Статья 3"));

        accidents.put(1, new Accident(1, "Accident1", "aaaaaa", "Street1", accidentTypes.get(1),
                new HashSet<>(List.of(rules.get(0)))));
        accidents.put(2, new Accident(2, "Accident2", "aaaaaa", "Street2", accidentTypes.get(2),
                new HashSet<>(List.of(rules.get(0), rules.get(1)))));
        accidents.put(3, new Accident(3, "Accident3", "aaaaaa", "Street3", accidentTypes.get(3),
                new HashSet<>(List.of(rules.get(0), rules.get(1), rules.get(2)))));
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public void save(Accident accident, int accidentTypeId, String[] ruleIds) {
        int id = ids.incrementAndGet();
        accident.setId(id);
        accident.setAccidentType(findByIdType(accidentTypeId));
        accident.setRules(
                Arrays.stream(ruleIds)
                        .map(ruleId -> findByIdRule(Integer.parseInt(ruleId)))
                        .collect(Collectors.toSet()));
        accidents.put(id, accident);
    }

    public Accident fidById(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident, int accidentTypeId, String[] ruleIds) {
        accident.setText(accident.getText());
        accident.setAccidentType(accidentTypes.get(accidentTypeId));
        accident.setRules(
                Arrays.stream(ruleIds)
                        .map(ruleId -> findByIdRule(Integer.parseInt(ruleId)))
                        .collect(Collectors.toSet()));
        accidents.replace(accident.getId(), accident);
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentTypes.values();
    }

    public AccidentType findByIdType(int id) {
        return accidentTypes.get(id);
    }

    public Set<Rule> getAllRules() {
        return new HashSet<>(rules);
    }

    public Rule findByIdRule(int id) {
        Rule rsl = null;
        for (Rule r : rules) {
            if (r.getId() == id) {
                rsl = r;
            }
        }
        return rsl;
    }

}
