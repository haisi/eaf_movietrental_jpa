package ch.fhnw.edu.rental.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be null.
     * @return the entity with the given id or null of none was found.
     * @throws IllegalArgumentException if id is null.
     */
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the
     * save operation might have changed the entity instance completely.
     *
     * @param entity to save
     * @return the saved entity
     */
    public T save(T entity) {
        return entityManager.merge(entity);
    }

    /**
     * Deletes a given entity.
     *
     * @param entity entity
     * @throws IllegalArgumentException in case the given entity is null.
     */
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Deletes the entity with the given id. Throws a runtime exception if the
     * entity with the given id could not be found.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given id is null
     */
    public void deleteById(ID id) {
        T t = findById(id).orElseThrow(IllegalArgumentException::new);
        delete(t);
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be null.
     * @return true if an entity with the given id exists, false otherwise
     * @throws IllegalArgumentException if id is null
     */
    public boolean existsById(ID id) {
        if (id == null) throw new IllegalArgumentException();
        return findById(id).isPresent();
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
