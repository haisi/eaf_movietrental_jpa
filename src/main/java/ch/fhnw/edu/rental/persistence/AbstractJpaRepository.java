package ch.fhnw.edu.rental.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Hasan Kara
 */
public abstract class AbstractJpaRepository<T, ID> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(ID id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(ID entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public long count() {
        // TODO get the entity name from the Entity-annotation
        TypedQuery<Long> query = entityManager.createQuery(String.format("select count(o) from %s o", clazz.getName()), Long.class);
        return query.getSingleResult();
    }


}
