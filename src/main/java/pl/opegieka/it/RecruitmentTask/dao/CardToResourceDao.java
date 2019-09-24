package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CardToResourceDao {

    public static final Logger LOG = LogManager.getLogger(CardToResourceDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public int findIfExist(Long cardId, Long resourceId) {
        final Query query = entityManager.createQuery("SELECT c FROM Card c " +
                "JOIN FETCH c.resourceList r WHERE c.id = :cardId and r.resourceId = :resourceId")
                .setParameter("cardId", cardId)
                .setParameter("resourceId", resourceId);

        LOG.debug(query.getResultList().toString());
        return query.getResultList().size();
    }


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
