package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Model.PermissionGroup;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PermissionGroupDaoImpl implements PermissionGroupDao<PermissionGroup> {

    public static final Logger LOG = LogManager.getLogger(PermissionGroupDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long save(PermissionGroup p) {
        entityManager.persist(p);
        return p.getGroupId();
    }

    @Override
    public PermissionGroup update(PermissionGroup p) {
        return entityManager.merge(p);
    }

    @Override
    public void delete(long id) {
        final PermissionGroup p = entityManager.find(PermissionGroup.class, id);
        if (p != null) {
            entityManager.remove(p);
        }
    }

    @Override
    public PermissionGroup findById(long id) {
        return entityManager.find(PermissionGroup.class, id);
    }

    @Override
    public List<PermissionGroup> findAll() {
        final Query query = entityManager.createQuery("SELECT p FROM PermissionGroup p");

        return query.getResultList();
    }

    @Override
    public int checkIfExist(String groupName) {
        final Query query = entityManager.createQuery("SELECT p FROM PermissionGroup p where p.groupName = :groupName");
        query.setParameter("groupName", groupName);
        List<PermissionGroup> groupList = query.getResultList();
        if (groupList.size() <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public PermissionGroup findByName(String name) {
        final Query query = entityManager.createQuery("SELECT p FROM PermissionGroup p where groupName = :name");
        query.setParameter("name", name);
        List<PermissionGroup> groupList = query.getResultList();
        if (groupList.size() <= 0) {
            throw new NotFoundException(String.format("Group %s not exist", name));
        }

        PermissionGroup permissionGroup = groupList.get(0);
        return permissionGroup;
    }
}
