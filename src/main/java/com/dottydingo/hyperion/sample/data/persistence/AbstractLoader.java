package com.dottydingo.hyperion.sample.data.persistence;

import com.dottydingo.hyperion.core.model.PersistentObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 */
public abstract class AbstractLoader<P extends PersistentObject<ID>,ID extends Serializable>
{
    @PersistenceContext()
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    protected abstract Class<P> getEntityClass();

    protected abstract Class<ID> getIdClass();

    public boolean exists(ID id)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(getEntityClass());

        cq.where(cb.equal(root.get("id"),id));
        cq.select(cb.count(root));

        return entityManager.createQuery(cq).getSingleResult() > 0;
    }

    public List<ID> exists(Collection<ID> ids)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ID> cq = cb.createQuery(getIdClass());
        Root<P> root = cq.from(getEntityClass());

        cq.where(root.get("id").in(ids));
        cq.select(root.<ID>get("id"));

        return entityManager.createQuery(cq).getResultList();
    }

    public P load(ID id)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<P> cq = cb.createQuery(getEntityClass());
        Root<P> root = cq.from(getEntityClass());

        cq.where(cb.equal(root.get("id"),id));

        List<P> results = entityManager.createQuery(cq).getResultList();
        if(results.size() == 0)
            return null;
        return results.get(0);
    }

    public boolean isUnique(String fieldName,String value)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<P> root = cq.from(getEntityClass());

        cq.where(cb.equal(root.get(fieldName),value));
        cq.select(cb.count(root));

        return entityManager.createQuery(cq).getSingleResult() == 0;
    }

    public ID findByField(String fieldName,Object value)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ID> cq = cb.createQuery(getIdClass());
        Root<P> root = cq.from(getEntityClass());

        cq.where(cb.equal(root.get(fieldName),value));
        cq.select(root.get("id"));

        TypedQuery<ID> query = entityManager.createQuery(cq);
        List<ID> resultList = query.getResultList();
        if(resultList.size() == 1)
            return  resultList.get(0);
        return null;
    }

    protected boolean isReferenced(String sqlQuery,Long id)
    {
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("id",id);
        return ((Number)query.getSingleResult()).intValue() > 0;
    }
}
