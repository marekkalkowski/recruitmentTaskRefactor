package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Model.Card;
import pl.opegieka.it.RecruitmentTask.Model.PermissionGroup;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CardToPermisionGroupDaoImpl implements CardToPermisionGroupDao<Card, PermissionGroup> {

    public static final Logger LOG = LogManager.getLogger(CardDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int findIfExist(Long cardId, Long groupId) {
        final Query query = entityManager.createQuery("SELECT c FROM Card c " +
                "JOIN FETCH c.permissionGroupList p WHERE c.id = :cardId and p.groupId = :groupId")
                .setParameter("cardId", cardId)
                .setParameter("groupId", groupId);

        return query.getResultList().size();
    }

    @Override
    public void delete(long cardId, long groupId) {

        if (findIfExist(cardId, groupId) == 0) {
            throw new NotFoundException("NO such card in group.");
        }

        entityManager.createNativeQuery("DELETE FROM opegieka.permission_groups_to_cards " +
                "where card_id = :cardId and permission_group_id = :groupId ")
                .setParameter("cardId", cardId)
                .setParameter("groupId", groupId)
                .executeUpdate();
    }
}
