package ru.job4j.accident.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentMapper implements RowMapper<Accident> {
    @Override
    public Accident mapRow(ResultSet rs, int i) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setAccidentType(new AccidentType(rs.getInt("id_type"), rs.getString("name_type")));
        return accident;
    }
}
