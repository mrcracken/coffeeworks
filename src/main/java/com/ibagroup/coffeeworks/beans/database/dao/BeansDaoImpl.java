package com.ibagroup.coffeeworks.beans.database.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ibagroup.coffeeworks.beans.database.dto.Beans;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */

/*
 * Use @RequestScoped
 * https://stackoverflow.com/questions/27149388/no-bean-is-eligible-for-injection-to-the-injection-point
 */
@RequestScoped
/*
 * We suppress the warning about not specifying a serialVersionUID, as we are still developing this app, and want the JVM to
 * generate the serialVersionUID for us. When we put this app into production, we'll generate and embed the serialVersionUID
 */
@SuppressWarnings("serial")
public class BeansDaoImpl implements Serializable {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
    private Event<Beans> beansEventSrc;
	
	/**
	 * <p> Return all beans if other parameters are null </p>
	 * @param search parameters
	 * @return list of beans
	 */
	public List<Beans> retrieveBeansByParams(BeansSearchParams params) throws NonUniqueResultException {
		if (params.getId() != null || params.getName() != null) {
			TypedQuery<Beans> query = em.createQuery("SELECT b FROM Beans b WHERE b.id = :id OR b.name = :name", Beans.class);
		  	  	if (params.getId() != null)
			  			query.setParameter("id", params.getId());
			  		else query.setParameter("id", null);
				if (params.getName() != null)
						query.setParameter("name", params.getName());
					else query.setParameter("name", null);
				try {
					return (List<Beans>) query.getResultList();
				} catch (NoResultException exc) {
					return null;
				}
		} else {
			TypedQuery<Beans> query = em.createQuery("SELECT DISTINCT b FROM Beans b ORDER BY b.id", Beans.class);
			try {
				return (List<Beans>) query.getResultList();
			} catch (NoResultException exc) {
				return null;
			}
		}
	}

//	Some code example	
//
//	public Order findOrderSubmittedAt(Date date) throws NonUniqueResultException {
//		Query q = entityManager.createQuery(
//			"SELECT e FROM " + entityClass.getName() + " e WHERE date = :date_at");
//		q.setParameter("date_at", date);
//		try {
//			return (Order) q.getSingleResult();
//		} catch (NoResultException exc) {
//			return null;
//		}
//	}
//	 
//	public Order getOrderSubmittedAt(Date date) throws NoResultException, NonUniqueResultException {
//		Query q = entityManager.createQuery(
//			"SELECT e FROM " + entityClass.getName() + " e WHERE date = :date_at");
//		q.setParameter("date_at", date);
//		return (Order) q.getSingleResult();
//	}
//	
//	
// https://xebia.com/blog/jpa-implementation-patterns-data-access-objects/#JpaDao
//
//	public class JpaOrderDao extends JpaDao<Integer, Order> implements OrderDao {
//		public List<Order> findOrdersSubmittedSince(Date date) {
//			Query q = entityManager.createQuery(
//				"SELECT e FROM " + entityClass.getName() + " e WHERE date >= :date_since");
//			q.setParameter("date_since", date);
//			return (List<Order>) q.getResultList();
//		}
//	}
//
	
    public void createNewBeans(Beans entity) throws Exception {
        em.persist(entity);
        beansEventSrc.fire(entity);
    }
    
	public void deleteBeansById(BeansSearchParams params) {
		Beans entity = em.find(Beans.class, params.getId());
	    if (entity == null) {
	    	entity = new Beans();
	    	}
	    em.remove(entity);
	   }

//	public Response update(Long id, CoffeeDto dto) {
//	      TypedQuery<Coffee> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM Coffee c WHERE c.id = :entityId ORDER BY c.id", Coffee.class);
//	      findByIdQuery.setParameter("entityId", id);
//	      Coffee entity;
//	      try {
//	         entity = findByIdQuery.getSingleResult();
//	      }
//	      catch (NoResultException nre) {
//	         entity = null;
//	      }
//	      entity = dto.fromDto(entity, em);
//	      try {
//	         entity = em.merge(entity);
//	      }
//	      catch (OptimisticLockException e) {
//	         return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
//	      }
//	      return Response.noContent().build();
//	   }
	 
}
