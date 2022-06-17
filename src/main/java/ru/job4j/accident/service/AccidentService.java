package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentMemStore;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMemStore accidentMemStore;

    public AccidentService(AccidentMemStore accidentMemStore) {
        this.accidentMemStore = accidentMemStore;
    }

    public Collection<Accident> getAll() {
        return accidentMemStore.getAll();
    }

    public void save(Accident accident) {
        accidentMemStore.save(accident);
    }

    public Accident findById(int id) {
        return accidentMemStore.fidById(id);
    }

    public void update(Accident accident) {
        accidentMemStore.update(accident);
    }
}
