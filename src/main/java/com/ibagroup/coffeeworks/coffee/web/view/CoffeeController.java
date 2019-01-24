package com.ibagroup.coffeeworks.coffee.web.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */
@Named
@ManagedBean
@SessionScoped
public class CoffeeController implements Serializable{
	
	@Inject
	private OnAddCoffeeButtonClick addCoffeeButtonClick;
	
	@Inject
	private OnDeleteCoffeeButtonClick deleteCoffeeButtonClick;
	
	public void addCoffeeButton() throws Exception {
		addCoffeeButtonClick.addButton();
	}
	
	public void deleteCoffeeButton(Long id) {
		deleteCoffeeButtonClick.deleteButtonClick(id);
	}
	
	public void onAddButtonClicked() {
		
		try {
			
		long id= 	facae/.addCoffe
			addInfor mes(record with id )
			
		} catch (Exception e) {
			addErro(plese )
			log.e
		}
	}
}
