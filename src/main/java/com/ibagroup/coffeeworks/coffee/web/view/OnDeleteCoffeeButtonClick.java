package com.ibagroup.coffeeworks.coffee.web.view;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.ibagroup.coffeeworks.coffee.service.CoffeeService;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */
@Model
public class OnDeleteCoffeeButtonClick {

	@Inject
	private CoffeeService service;
	
	public void deleteButtonClick(Long id) {
		System.out.println("Deleted");
		service.deleteCoffeeById(id);
	}
	
}
