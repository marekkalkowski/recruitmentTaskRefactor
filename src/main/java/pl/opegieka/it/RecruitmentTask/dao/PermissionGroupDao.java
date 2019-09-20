package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Repository.PermissionGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PermissionGroupDao {

    public static final Logger LOG = LogManager.getLogger(PermissionGroupDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public int save(PermissionGroup p) {
        entityManager.persist(p);
        return p.getGroupId();
    }

    public PermissionGroup update(PermissionGroup p) {
        return entityManager.merge(p);
    }

    public void delete(int id) {
        final PermissionGroup p = entityManager.find(PermissionGroup.class, id);
        if (p != null) {
            entityManager.remove(p);
        }
    }

    public PermissionGroup findById(int id) {
        return entityManager.find(PermissionGroup.class, id);
    }

    public List<PermissionGroup> findAll() {
        final Query query = entityManager.createQuery("SELECT p FROM PermissionGroup p");

        return query.getResultList();
    }

}
