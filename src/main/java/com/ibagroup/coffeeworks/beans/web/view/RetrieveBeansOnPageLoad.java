package com.ibagroup.coffeeworks.beans.web.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibagroup.coffeeworks.beans.service.crud.BeansCrudService;
import com.ibagroup.coffeeworks.beans.web.bean.BeansBean;
import com.ibagroup.coffeeworks.beans.web.mapper.BeansBeanMapper;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */

@RequestScoped
public class RetrieveBeansOnPageLoad {

	 @Inject
	 private BeansCrudService service;
	 
	 private List<BeansBean> beans;

	 /**
	  * <p>An entity mapper for map {@link BeansBean} and {@link Beans}
	  */
	 private BeansBeanMapper mapper = new BeansBeanMapper();
	 
	 // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
	 // Facelets or JSP view)
	 @Produces
	 @Named("retrieveBeans")
	 public List<BeansBean> getBeans() {
		 return beans;
	 }

	 public void onBeansListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final BeansBean beans) {
		 retrieveAllBeansOrderedById();
	 }

	 @PostConstruct
	 public void retrieveAllBeansOrderedById() {
	     beans = mapper.toBeanList(service.retrieveAllBeans());
	 }

}

