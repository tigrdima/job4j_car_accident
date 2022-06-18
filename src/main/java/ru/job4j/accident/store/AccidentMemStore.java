package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemStore {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final List<AccidentType> accidentTypes = new ArrayList<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMemStore() {
        accidentTypes.add(new AccidentType(1,  "Две машины"));
        accidentTypes.add(new AccidentType(2,  "Машина и человек"));
        accidentTypes.add(new AccidentType(3,  "Машина и велосипед"));
        accidents.put(1, new Accident(1, "Accident1", "aaaaaa", "Street1", findByIdType(1)));
        accidents.put(2, new Accident(2, "Accident2", "aaaaaa", "Street2", findByIdType(2)));
        accidents.put(3, new Accident(3, "Accident3", "aaaaaa", "Street3", findByIdType(3)));
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public void save(Accident accident, int accidentTypeId) {
        int id = ids.incrementAndGet();
        accident.setId(id);
        accident.setAccidentType(findByIdType(accidentTypeId));
        accidents.put(id, accident);
    }

    public Accident fidById(int id) {
       return accidents.get(id);
    }

    public void update(Accident accident, int accidentTypeId) {
        accident.setText(accident.getText());
        accident.setAccidentType(findByIdType(accidentTypeId));
        accidents.replace(accident.getId(), accident);
    }

    public List<AccidentType> getAllTypes() {
        return new ArrayList<>(accidentTypes);
    }

    public AccidentType findByIdType(int id) {
        AccidentType rsl = null;
        for (AccidentType at : accidentTypes) {
            if (at.getId() == id) {
                rsl = at;
            }
        }
        return rsl;
    }
}
