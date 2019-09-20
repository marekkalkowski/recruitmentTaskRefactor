package pl.opegieka.it.RecruitmentTask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.opegieka.it.RecruitmentTask.Repository.Resource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ResourceDao {

    public static final Logger LOG = LogManager.getLogger(ResourceDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Resource r) {
        entityManager.persist(r);
        return r.getResourceId();
    }

    public Resource update(Resource r) {
        return entityManager.merge(r);
    }

    public void delete(int id) {
        final Resource r = entityManager.find(Resource.class, id);
        if (r != null) {
            entityManager.remove(r);
        }
    }

    public Resource findById(int id) {
        return entityManager.find(Resource.class, id);
    }

    public List<Resource> findAll() {
        final Query query = entityManager.createQuery("SELECT r FROM Resource r");

        return query.getResultList();
    }


}
