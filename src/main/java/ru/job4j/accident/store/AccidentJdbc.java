package ru.job4j.accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbc {
    private final JdbcTemplate jdbc;

    public AccidentJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rule> get(int id) {
        return jdbc.query("select r.id as id_rule, r.name as name_rule from accident"
                        + "join accident_rule ar on ar.accident_id = accident.id"
                        + "join rule r on ar_id = r.id"
                        + "where accident.id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id_rule"));
                    rule.setName(rs.getString("name_rule"));
                    return rule;
                }, id);
    }

    public List<Accident> getAll() {
        return jdbc.query("select accident.id, accident.name, text, address, at.id as id_type, at.name as name_type from accident"
                        + "join accident_type at on accident.accident_type_id = at.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setAccidentType(new AccidentType(rs.getInt("id_type"), rs.getString("name_type")));
                    accident.setRules(new HashSet<>(get(accident.getId())));
                    return accident;
                });
    }
}
