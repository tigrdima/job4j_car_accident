package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.store.AccidentMemStore;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {
    private final AccidentMemStore accidentMemStore;

    public AccidentService(AccidentMemStore accidentMemStore) {
        this.accidentMemStore = accidentMemStore;
    }

    public Collection<Accident> getAll() {
        return accidentMemStore.getAll();
    }

    public void save(Accident accident, int accidentTypeId) {
        accidentMemStore.save(accident, accidentTypeId);
    }

    public Accident findById(int id) {
        return accidentMemStore.fidById(id);
    }

    public void update(Accident accident, int accidentTypeId) {
        accidentMemStore.update(accident, accidentTypeId);
    }

    public List<AccidentType> getAllTypes() {
        return accidentMemStore.getAllTypes();
    }

    public AccidentType findByIdType(int id) {
        return accidentMemStore.findByIdType(id);
    }

}
