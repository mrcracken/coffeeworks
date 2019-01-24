package com.ibagroup.coffeeworks.coffee.web.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;
import com.ibagroup.coffeeworks.coffee.service.CoffeeService;
import com.ibagroup.coffeeworks.coffee.web.bean.CoffeeBean;
import com.ibagroup.coffeeworks.coffee.web.mapper.CoffeeBeanMapper;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */

@RequestScoped
public class RetrieveAllCoffeeOnPageLoad {

	 @Inject
	 private CoffeeService service;
	 
	 private List<CoffeeBean> coffees;

	 /**
	  * <p>An entity mapper for map {@link CoffeeBean} and {@link Coffee}
	  */
	 private CoffeeBeanMapper mapper = new CoffeeBeanMapper();
	 
	 // @Named provides access the return value via the EL variable name "retrieveCoffee" in the UI (e.g.
	 // Facelets or JSP view)
	 @Produces
	 @Named("retrieveCoffee")
	 public List<CoffeeBean> getCoffees() {
		 return coffees;
	 }

	 public void onCoffeeListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final CoffeeBean coffees) {
	     retrieveAllCoffeesOrderedById();
	 }

	 @PostConstruct
	 public void retrieveAllCoffeesOrderedById() {
	     coffees = mapper.toBeanList(service.retrieveAllCoffee());
	 }

}
