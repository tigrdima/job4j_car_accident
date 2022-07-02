package ru.job4j.accident.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentHbm {
   private final SessionFactory sf;

    public AccidentHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select a from Accident a join fetch a.rules", Accident.class).list();
        }
    }

    public AccidentType findForAccident(int id) {
        try (Session session = sf.openSession()) {
          return (AccidentType) session.createQuery("from AccidentType at where id = :atId")
                  .setParameter("atId", id)
                  .uniqueResult();
        }
    }

    public void save(Accident accident, int accidentTypeId, String[] ruleIds) {
        try (Session session = sf.openSession()) {
            for (String ruleId : ruleIds) {
               Rule rule = session.createQuery("from Rule r where id = :rId", Rule.class)
                        .setParameter("rId", Integer.parseInt(ruleId))
                        .uniqueResult();
                accident.getRules().add(rule);
            }
            accident.setAccidentType(findForAccident(accidentTypeId));
            session.save(accident);
        }
    }

    public List<AccidentType> getAllTypes() {
        try (Session session = sf.openSession()) {
           return session.createQuery("from AccidentType", AccidentType.class).list();
        }
    }

    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule", Rule.class).list();
        }
    }
}
