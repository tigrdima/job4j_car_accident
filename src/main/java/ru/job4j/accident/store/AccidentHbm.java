package ru.job4j.accident.store;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

/**
@Repository
public class AccidentHbm implements SessionHbm {
    private final SessionFactory sf;

    public AccidentHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Accident> getAll() {
        return sessionApply(session -> session.createQuery("select distinct a from Accident a join fetch a.rules", Accident.class).list(), sf);
    }

    public AccidentType findTypeByAccidentId(int id) {
        return sessionApply(session ->
                session.createQuery("from AccidentType at where id = :atId", AccidentType.class)
                        .setParameter("atId", id)
                        .uniqueResult(), sf);
    }

    public void saveOrUpdateAccident(Accident accident, int accidentTypeId, String[] ruleIds) {
        sessionApply(session -> {
            for (String ruleId : ruleIds) {
                Rule rule = session.createQuery("from Rule r where id = :rId", Rule.class)
                        .setParameter("rId", Integer.parseInt(ruleId))
                        .uniqueResult();
                accident.getRules().add(rule);
            }
            accident.setAccidentType(findTypeByAccidentId(accidentTypeId));
            session.saveOrUpdate(accident);
            return accident;
        }, sf);
    }

    public List<AccidentType> getAllTypes() {
        return sessionApply(session -> session.createQuery("from AccidentType", AccidentType.class).list(), sf);
    }

    public List<Rule> getAllRules() {
        return sessionApply(session -> session.createQuery("from Rule", Rule.class).list(), sf);
    }

    public Accident findAccidentById(int id) {
        return sessionApply(session -> session.createQuery("select distinct a from Accident a join fetch a.rules "
                        + "join fetch a.accidentType where a.id = :aId", Accident.class)
                .setParameter("aId", id)
                .uniqueResult(), sf);
    }
}*/
