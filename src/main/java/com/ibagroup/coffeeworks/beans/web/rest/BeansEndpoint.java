package com.ibagroup.coffeeworks.beans.web.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ibagroup.coffeeworks.beans.database.dao.BeansSearchParams;
import com.ibagroup.coffeeworks.beans.database.dto.Beans;
import com.ibagroup.coffeeworks.beans.service.crud.BeansCrudService;
import com.ibagroup.coffeeworks.beans.web.bean.BeansBean;
import com.ibagroup.coffeeworks.beans.web.mapper.BeansBeanMapper;
import com.ibagroup.coffeeworks.beans.web.mapper.BeansEntityMapper;

/**
 * <p>A Jax-Rs endpoint for {@link Beans}</p>
 * 
 * @author IBA Group
 * @since 2019
 *
 */
@Path("beans")
@Stateless
public class BeansEndpoint {
	
	@Inject
	private BeansCrudService service;
	
	/**
	 * <p>A bean mapper for map {@link Beans} and {@link BeansBean}
	 */
	private BeansBeanMapper mapperToBean = new BeansBeanMapper();
	
	/**
	 * <p>An entity mapper for map {@link BeansBean} and {@link Beans}
	 */
	private BeansEntityMapper mapperToEntity = new BeansEntityMapper();
    
    @GET
    @Path("/{name}")
    @Produces("application/json")
    public List<BeansBean> findBeansByName(@PathParam("name") String name) {
    	BeansSearchParams params = new BeansSearchParams();
    	params.setName(name);
    	return mapperToBean.toBeanList(service.retrieveBeansByName(name));
    }
    
    @GET
    @Produces("application/json")
    public List<BeansBean> retriveAllBeans() {
    	return mapperToBean.toBeanList(service.retrieveAllBeans());
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public List<BeansBean> findBeansById(@PathParam("id") Long id) {
    	BeansSearchParams params = new BeansSearchParams();
    	params.setId(id);
    	return mapperToBean.toBeanList(service.retrieveBeansById(id));
    }
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public void deleteBeansById(@PathParam("id") Long id) {
    	BeansSearchParams params = new BeansSearchParams();
    	params.setId(id);
    	service.deleteBeansById(id);
    }
    
    
    @POST
    @Consumes("application/json")
    public void createNewBeans(BeansBean bean) throws Exception {
    	service.createNewBeans(mapperToEntity.toEntity(bean));
    }
	
//    @PUT
//    @Path("/{id:[0-9][0-9]*}")
//    @Consumes("application/json")
//    public Response update(@PathParam("id") Long id, CoffeeDto dto) {
//    	return dao.update(id, dto);
//    }
	 
}
