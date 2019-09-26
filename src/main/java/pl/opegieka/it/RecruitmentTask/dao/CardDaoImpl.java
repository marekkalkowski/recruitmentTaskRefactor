package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Model.Card;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CardDaoImpl implements CardDao<Card> {

    public static final Logger LOG = LogManager.getLogger(CardDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long save(Card c) {
        entityManager.persist(c);
        LOG.info("id karty: " + c.getCardId());
        return c.getCardId();
    }


    @Override
    public Card update(Card c) {
        return entityManager.merge(c);
    }

    @Override
    public void delete(long id) {
        final Card c = entityManager.find(Card.class, id);
        if (c != null) {
            entityManager.remove(c);
        }
    }

    @Override
    public Card findById(long id) {
        return entityManager.find(Card.class, id);
    }

    @Override
    public List<Card> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM Card c");
        return query.getResultList();
    }

    @Override
    public int checkIfExist(int cardNumber) {
        final Query query = entityManager.createQuery("SELECT c FROM Card c where c.cardNumber = :cardNumber");
        query.setParameter("cardNumber", cardNumber);
        List<Card> groupList = query.getResultList();
        if (groupList.size() <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public Card findByCardNumber(int cardNumber) {
        final Query query = entityManager.createQuery("SELECT c FROM Card c where c.cardNumber = :cardNumber");
        query.setParameter("cardNumber", cardNumber);
        List<Card> groupList = query.getResultList();
        if (groupList.size() <= 0) {
            throw new NotFoundException("Card not found");
        }
        return findById(groupList.get(0).getCardId());
    }
}
