package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Model.Card;
import pl.opegieka.it.RecruitmentTask.Model.Resource;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CardToResourceDaoImpl implements CardToResourceDao<Card, Resource> {

    public static final Logger LOG = LogManager.getLogger(CardToResourceDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int findIfExist(long cardId, long resourceId) {
        final Query query = entityManager.createQuery("SELECT c FROM Card c " +
                "JOIN FETCH c.resourceList r WHERE c.id = :cardId and r.resourceId = :resourceId")
                .setParameter("cardId", cardId)
                .setParameter("resourceId", resourceId);

        LOG.debug(query.getResultList().toString());
        return query.getResultList().size();
    }


    @Override
    public void delete(long cardId, long resourceId) {

        if (findIfExist(cardId, resourceId) == 0) {
            throw new NotFoundException("NO such card in resource.");
        }

        entityManager.createNativeQuery("DELETE FROM opegieka.resources_to_cards " +
                "where card_id = :cardId and resource_id = :resourceId ")
                .setParameter("cardId", cardId)
                .setParameter("resourceId", resourceId)
                .executeUpdate();
    }
}
