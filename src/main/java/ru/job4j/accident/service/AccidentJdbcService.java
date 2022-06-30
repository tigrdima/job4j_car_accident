package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentJdbc;

import java.util.List;

@Service
public class AccidentJdbcService {
    private final AccidentJdbc accidentJdbc;

    public AccidentJdbcService(AccidentJdbc accidentJdbc) {
        this.accidentJdbc = accidentJdbc;
    }

    public List<Accident> getAll() {
        return accidentJdbc.getAll();
    }
}
