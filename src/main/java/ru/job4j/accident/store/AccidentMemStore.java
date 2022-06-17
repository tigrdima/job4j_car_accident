package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMemStore {
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMemStore() {
        accidents.put(1, new Accident(1, "Accident1", "aaaaaa", "Street1"));
        accidents.put(2, new Accident(2, "Accident2", "aaaaaa", "Street2"));
        accidents.put(3, new Accident(3, "Accident3", "aaaaaa", "Street3"));
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }
}
