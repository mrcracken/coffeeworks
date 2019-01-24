package com.ibagroup.coffeeworks.coffee.web.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ibagroup.coffeeworks.coffee.database.dao.CoffeeSearchParams;
import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;
import com.ibagroup.coffeeworks.coffee.service.CoffeeService;
import com.ibagroup.coffeeworks.coffee.web.bean.CoffeeBean;
import com.ibagroup.coffeeworks.coffee.web.mapper.CoffeeBeanMapper;
import com.ibagroup.coffeeworks.coffee.web.mapper.CoffeeEntityMapper;

/**
 * 
 * @author IBA Group
 * @since 2018
 * 
 * An REST service for {@link Sample}
 *
 */

@Path("coffee")
@Stateless
public class CoffeeEndpoint {
	
	@Inject
	private CoffeeService service;
	
	/**
	 * <p>A bean mapper for map {@link Coffee} and {@link CoffeeBean}
	 */
	private CoffeeBeanMapper mapperToBean = new CoffeeBeanMapper();
	
	/**
	 * <p>An entity mapper for map {@link CoffeeBean} and {@link Coffee}
	 */
	private CoffeeEntityMapper mapperToEntity = new CoffeeEntityMapper();
    
    @GET
    @Path("/{name}")
    @Produces("application/json")
    public List<CoffeeBean> findByName(@PathParam("name") String name) {
    	CoffeeSearchParams params = new CoffeeSearchParams();
    	params.setName(name);
    	return mapperToBean.toBeanList(service.retrieveCoffeeByName(name));
    }
    
    @GET
    @Produces("application/json")
    public List<CoffeeBean> retriveAllCoffee() {
    	return mapperToBean.toBeanList(service.retrieveAllCoffee());
    }
    
    @GET
    @Path("/bean/{beanName}")
    @Produces("application/json")
    public List<CoffeeBean> findCoffeeByBeanName(@PathParam("beanName") String beanName) {
    	CoffeeSearchParams params = new CoffeeSearchParams();
    	params.setBeanName(beanName);
    	return mapperToBean.toBeanList(service.retrieveCoffeeByBeanName(beanName));
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public List<CoffeeBean> findCoffeeById(@PathParam("id") Long id) {
    	return mapperToBean.toBeanList(service.retrieveCoffeeById(id));
    }
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteById(@PathParam("id") Long id) {
    	service.deleteCoffeeById(id);
    }
    
    
    @POST
    @Consumes("application/json")
    public void createNewCoffee(CoffeeBean bean) throws Exception {
    	service.createNewCoffee(mapperToEntity.toEntity(bean));
    }
	
//    @PUT
//    @Path("/{id:[0-9][0-9]*}")
//    @Consumes("application/json")
//    public Response update(@PathParam("id") Long id, CoffeeDto dto) {
//    	return dao.update(id, dto);
//    }
	 
}