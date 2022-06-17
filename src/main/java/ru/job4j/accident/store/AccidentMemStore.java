package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemStore {
    private final Map<Integer, Accident> accidents;
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMemStore(Map<Integer, Accident> accidents) {
        this.accidents = accidents;
        accidents.put(1, new Accident(1, "Accident1", "aaaaaa", "Street1"));
        accidents.put(2, new Accident(2, "Accident2", "aaaaaa", "Street2"));
        accidents.put(3, new Accident(3, "Accident3", "aaaaaa", "Street3"));
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public void save(Accident accident) {
        int id = ids.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public Accident fidById(int id) {
       return accidents.get(id);
    }

    public void update(Accident accident) {
        accident.setText(accident.getText());
        accidents.replace(accident.getId(), accident);
    }
}
