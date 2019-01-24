package com.ibagroup.coffeeworks.coffee.web.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibagroup.coffeeworks.coffee.database.dto.Coffee;
import com.ibagroup.coffeeworks.coffee.service.CoffeeService;
import com.ibagroup.coffeeworks.coffee.web.bean.CoffeeBean;
import com.ibagroup.coffeeworks.coffee.web.mapper.CoffeeEntityMapper;

/**
 * 
 * <p>Add a new kind of coffee</p>
 * 
 * @author IBA Group
 * @since 2019
 *
 */
@Model
public class OnAddCoffeeButtonClick {
	
	@Inject
    private FacesContext facesContext;
	
	@Produces
	@Named("coffeeBean")
	private CoffeeBean coffeeBean;
	
	/**
	 * <p>An entity mapper for map {@link CoffeeBean} and {@link Coffee}
	 */
	private CoffeeEntityMapper mapper = new CoffeeEntityMapper();
	
	@Inject
	private CoffeeService service;
	
	/*
	 * https://stackoverflow.com/questions/16542003/jsf-what-is-the-difference-between-postconstruct-and-direct-method-call-from
	 */
	@PostConstruct
	public void initNewCoffee() {
		coffeeBean = new CoffeeBean();
	}
	
	public void addButton() throws Exception {
		try {
			service.createNewCoffee(mapper.toEntity(coffeeBean));
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initNewCoffee();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }

    }
	
	private String getRootErrorMessage(Exception e) {
	        // Default to general error message that registration failed.
	        String errorMessage = "Registration failed. See server log for more information";
	        if (e == null) {
	            // This shouldn't happen, but return the default messages
	            return errorMessage;
	        }

	        // Start with the exception and recurse to find the root cause
	        Throwable t = e;
	        while (t != null) {
	            // Get the message from the Throwable class instance
	            errorMessage = t.getLocalizedMessage();
	            t = t.getCause();
	        }
	        // This is the root cause message
	        return errorMessage;
	}
	
}
