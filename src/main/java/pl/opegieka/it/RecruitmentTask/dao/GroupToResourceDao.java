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
public class GroupToResourceDao {

    public static final Logger LOG = LogManager.getLogger(GroupToResourceDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public int findIfExist(Long groupId, Long resourceId) {
        final Query query = entityManager.createQuery("SELECT p FROM PermissionGroup p " +
                "JOIN FETCH p.resourceList r WHERE p.id = :groupId and r.resourceId = :resourceId")
                .setParameter("groupId", groupId)
                .setParameter("resourceId", resourceId);

        LOG.debug(query.getResultList().toString());
        return query.getResultList().size();
    }


    public void delete(long groupId, long resourceId) {

        if (findIfExist(groupId, resourceId) == 0) {
            throw new NotFoundException("NO such group in resource.");
        }

       entityManager.createNativeQuery("DELETE FROM opegieka.permission_groups_to_resources " +
                "where permission_group_id = :groupId and resource_id = :resourceId ")
                .setParameter("groupId", groupId)
               .setParameter("resourceId", resourceId)
               .executeUpdate();

    }
}
