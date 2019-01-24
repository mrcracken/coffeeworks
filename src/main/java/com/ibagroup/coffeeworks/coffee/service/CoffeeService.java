package com.ibagroup.coffeeworks.coffee.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.ibagroup.coffeeworks.coffee.database.dao.CoffeeDaoImpl;
import com.ibagroup.coffeeworks.coffee.database.dao.CoffeeSearchParams;
import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;

/**
 * 
 * @author IBA Group
 * @since 2019
 * 
 * An CRUD service for {@link Coffee}
 *
 */
@Path("coffee/test")
@Stateless
public class CoffeeService {
	
	@Inject
	CoffeeDaoImpl dao;
	
	/*
	 * Some test method for invoke all coffee by params
	 */
	public List<Coffee> retrieveCoffee(CoffeeSearchParams params){
		return dao.retrieveCoffeeByParams(params);
	}
    
    public List<Coffee> retrieveCoffeeByName(String name) {
    	CoffeeSearchParams params = new CoffeeSearchParams();
    	params.setName(name);
    	return dao.retrieveCoffeeByParams(params);
    }
    
    public List<Coffee> retrieveCoffeeById(Long id) {
    	CoffeeSearchParams params = new CoffeeSearchParams();
    	params.setId(id);
    	return dao.retrieveCoffeeByParams(params);
    }
    
    public List<Coffee> retrieveAllCoffee() {
    	CoffeeSearchParams params = new CoffeeSearchParams();
    	return dao.retrieveCoffeeByParams(params);
    }
    
    public List<Coffee> retrieveCoffeeByBeanName(String beanName) {
    	CoffeeSearchParams params = new CoffeeSearchParams();
    	params.setBeanName(beanName);
    	return dao.retrieveCoffeeByParams(params);
    }
 
    public void deleteCoffeeById(Long id) {
    	dao.deleteCoffeeById(id);
    }
    
    public void createNewCoffee(Coffee entity) throws Exception {
    	dao.createNewCoffee(entity);;
    }
    
//    Doesn't work in CoffeeDaoImpl for now
//    
//    @PUT
//    @Path("/{id:[0-9][0-9]*}")
//    @Consumes("application/json")
//    public void updateCoffeeById(@PathParam("id") Long id, Coffee entity) {
//    	dao.updateCoffeeById(id, entity);
//    }

}
