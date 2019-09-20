package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Repository.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
@Transactional
public class CardDao {

    public static final Logger LOG = LogManager.getLogger(CardDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Card c) throws InvocationTargetException {
        entityManager.persist(c);
        return c.getCardId();
    }

    public Card update(Card c) {
        return entityManager.merge(c);
    }

    public void delete(int id) {
        final Card c = entityManager.find(Card.class, id);
        if (c != null) {
            entityManager.remove(c);
        }
    }

    public Card findById(int id) {
        return entityManager.find(Card.class, id);
    }

    public List<Card> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM Card c");

        return query.getResultList();
    }
}
