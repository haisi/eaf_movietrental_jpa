package ch.fhnw.edu.rental.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public List<T> findWithNamedQuery(String namedQueryName) {
        return this.entityManager.createNamedQuery(namedQueryName, clazz).getResultList();
    }

    public List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    public List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return this.entityManager.createNamedQuery(queryName, clazz)
            .setMaxResults(resultLimit)
            .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByNativeQuery(String sql) {
        return this.entityManager.createNativeQuery(sql, clazz).getResultList();
    }

    public List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = this.entityManager.createNamedQuery(namedQueryName, clazz);

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }

        rawParameters.forEach(tuple -> query.setParameter(tuple.getKey(), tuple.getValue()));

        return query.getResultList();
    }

    public List<T> findWithQuery(String namedQueryName) {
        return this.entityManager.createQuery(namedQueryName, clazz).getResultList();
    }

    public List<T> findWithQuery(String namedQueryName, Map<String, Object> parameters) {
        return findWithQuery(namedQueryName, parameters, 0);
    }

    public List<T> findWithQuery(String queryName, int resultLimit) {
        return this.entityManager.createQuery(queryName, clazz)
            .setMaxResults(resultLimit)
            .getResultList();
    }

    public List<T> findWithQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = this.entityManager.createQuery(namedQueryName, clazz);

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }

        rawParameters.forEach(tuple -> query.setParameter(tuple.getKey(), tuple.getValue()));

        return query.getResultList();
    }

}
