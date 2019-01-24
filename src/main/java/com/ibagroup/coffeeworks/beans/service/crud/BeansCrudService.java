package com.ibagroup.coffeeworks.beans.service.crud;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ibagroup.coffeeworks.beans.database.dao.BeansDaoImpl;
import com.ibagroup.coffeeworks.beans.database.dao.BeansSearchParams;
import com.ibagroup.coffeeworks.beans.database.dto.Beans;

@Stateless
public class BeansCrudService {

	@Inject
	BeansDaoImpl dao;
    
    public List<Beans> retrieveBeansByName(String name) {
    	BeansSearchParams params = new BeansSearchParams();
    	params.setName(name);
    	return dao.retrieveBeansByParams(params);
    }
    
    public List<Beans> retrieveBeansById(Long id) {
    	BeansSearchParams params = new BeansSearchParams();
    	params.setId(id);
    	return dao.retrieveBeansByParams(params);
    }
    
    public List<Beans> retrieveAllBeans() {
    	BeansSearchParams params = new BeansSearchParams();
    	return dao.retrieveBeansByParams(params);
    }
 
    public void deleteBeansById(Long id) {
    	BeansSearchParams params = new BeansSearchParams();
    	params.setId(id);
    	dao.deleteBeansById(params);
    }
    
    public void createNewBeans(Beans entity) throws Exception {
    	dao.createNewBeans(entity);
    }
    
	
//    @PUT
//    @Path("/{id:[0-9][0-9]*}")
//    @Consumes("application/json")
//    public void update(@PathParam("id") Long id, Coffee entity) {
//    	dao.update(id, entity);
//    }
	
}
