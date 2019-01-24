package com.ibagroup.coffeeworks.coffee.database.dao;

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

import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;

//	Use @RequestScoped
//	https://stackoverflow.com/questions/27149388/no-bean-is-eligible-for-injection-to-the-injection-point

/*
 * We suppress the warning about not specifying a serialVersionUID, as we are still developing this app, and want the JVM to
 * generate the serialVersionUID for us. When we put this app into production, we'll generate and embed the serialVersionUID
 */
@SuppressWarnings("serial")
@RequestScoped
public class CoffeeDaoImpl implements Serializable {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
    private Event<Coffee> coffeeEventSrc;
	
	/**
	 * <p> Return all coffee if other parameters are null </p>
	 * @param search parameters
	 * @return list of coffee
	 */
	public List<Coffee> retrieveCoffeeByParams(CoffeeSearchParams params) throws NonUniqueResultException {
		if (params.getId() != null || params.getName() != null || params.getLocation() != null || params.getStars() != null || params.getDescription() != null || params.getBeanName() != null) {
			TypedQuery<Coffee> query = em.createQuery("SELECT c FROM Coffee c WHERE c.id = :id OR c.name = :name OR c.location = :location OR c.stars = :stars OR c.description = :description OR c.beans.name = :beanName", Coffee.class);
		  	  	if (params.getId() != null)
			  			query.setParameter("id", params.getId());
			  		else query.setParameter("id", null);
				if (params.getName() != null)
						query.setParameter("name", params.getName());
					else query.setParameter("name", null);
				if (params.getLocation() != null)
						query.setParameter("location", params.getLocation());
					else  query.setParameter("location", null);
				if (params.getStars() != null)
						query.setParameter("stars", params.getStars());
					else query.setParameter("stars", null);
				if (params.getDescription() != null)
						query.setParameter("description", params.getDescription());
					else query.setParameter("description", null);
				if (params.getBeanName() != null)
					query.setParameter("beanName", params.getBeanName());
				else query.setParameter("beanName", null);
				try {
					return (List<Coffee>) query.getResultList();
				} catch (NoResultException exc) {
					return null;
				}
		} else {
			TypedQuery<Coffee> query = em.createQuery("SELECT DISTINCT c FROM Coffee c ORDER BY c.id", Coffee.class);
			try {
				return (List<Coffee>) query.getResultList();
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

    public void createNewCoffee(Coffee entity) throws Exception {
        em.persist(entity);
        coffeeEventSrc.fire(entity);
    }

    public void deleteCoffeeById(Long id) {
		  Coffee entity = em.find(Coffee.class, id);
	      if (entity == null) {
	         entity = new Coffee();
	      }
	      em.remove(entity);
	   }
    
    /*
     * Doesn't work!!! TODO
     */
	public void updateCoffeeById(Long id, Coffee entityToUpdate) {
	      TypedQuery<Coffee> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM Coffee c WHERE c.id = :entityId ORDER BY c.id", Coffee.class);
	      findByIdQuery.setParameter("entityId", id);
	      Coffee entity;
	      try {
	         entity = findByIdQuery.getSingleResult();
	      }
	      catch (NoResultException nre) {
	         entity = null;
	      }
	      entity = entityToUpdate;
	      em.persist(entity);
//	      coffeeEventSrc.fire(entity);
	   }
	
}
	