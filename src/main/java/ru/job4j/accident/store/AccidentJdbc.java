package ru.job4j.accident.store;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.mapper.AccidentMapper;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
/**
@Repository
public class AccidentJdbc {
    private final JdbcTemplate jdbc;

    public AccidentJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Accident accident, int accidentType, String[] ruleIds) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "insert into accident (name, text, address, accident_type_id ) values (?, ?, ?, ?)";
        jdbc.update(con -> {
                    PreparedStatement pr = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    pr.setString(1, accident.getName());
                    pr.setString(2, accident.getText());
                    pr.setString(3, accident.getAddress());
                    pr.setInt(4, accidentType);
                    return pr;
                }, keyHolder);
        Integer accidentId = (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
        saveAccidentRule(ruleIds, accidentId);
    }

    public List<Accident> getAll() {
        List<Accident> accidents = jdbc.query("select a.id, a.name, a.text, a.address, at.id as id_type, at.name as name_type from accident as a "
                + "inner join accident_type at on at.id = a.accident_type_id ", new AccidentMapper());
        accidents.forEach(ac -> ac.setRules(new HashSet<>(getRulesForAccident(ac.getId()))));
        accidents.sort(Comparator.comparingInt(Accident::getId));
        return accidents;
    }

    public List<AccidentType> getAllTypes() {
        return jdbc.query("select * from accident_type", new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public List<Rule> getAllRules() {
        return jdbc.query("select * from rule", new BeanPropertyRowMapper<>(Rule.class));
    }

    public Accident findById(int id) {
        Accident accident = jdbc.query("select a.id, a.name, a.text, a.address, at.id as id_type, at.name as name_type from accident as a "
                        + "inner join accident_type at on at.id = a.accident_type_id where a.id = ?", new Integer[]{id}, new AccidentMapper())
                .stream()
                .findAny()
                .orElse(null);
        Objects.requireNonNull(accident).setRules(new HashSet<>(getRulesForAccident(id)));
        return accident;
    }

    public void update(Accident accident, int accidentTypeId, String[] ruleIds) {
        int accidentId = accident.getId();
        jdbc.update("update accident set name = ?, text = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accidentTypeId, accidentId);
        deleteAccidentRule(accidentId);
        saveAccidentRule(ruleIds, accidentId);
    }

    private List<Rule> getRulesForAccident(int id) {
        return jdbc.query("select rule.id, rule.name from rule "
                + "inner join accident_rule ar on ar.rule_id = rule.id "
                + "where ar.accident_id = ?", new Integer[] {id}, new BeanPropertyRowMapper<>(Rule.class));
    }

    private void deleteAccidentRule(int id) {
        jdbc.update("delete from accident_rule where accident_id = ?", id);
    }

    private void saveAccidentRule(String[] ruleIds, int id) {
        for (String ruleId : ruleIds) {
            jdbc.update("insert into accident_rule (rule_id, accident_id) values (?, ?)",
                    Integer.parseInt(ruleId), id);
        }
    }
}*/
