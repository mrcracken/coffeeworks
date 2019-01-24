package com.ibagroup.coffeeworks.beans.web.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibagroup.coffeeworks.beans.service.crud.BeansCrudService;
import com.ibagroup.coffeeworks.beans.web.bean.BeansBean;
import com.ibagroup.coffeeworks.beans.web.mapper.BeansEntityMapper;

/**
 * 
 * <p>Add a new kind of coffee bean</p>
 * 
 * @author IBA Group
 * @since 2019
 *
 */
@Model
public class OnButtonAddBeanClick {
	
	@Inject
    private FacesContext facesContext;
	
	@Produces
	@Named("beansBean")
	private BeansBean beansBean;
	
	/**
	 * <p>An entity mapper for map {@link BeansBean} and {@link Beans}
	 */
	private BeansEntityMapper mapper = new BeansEntityMapper();
	
	@Inject
	private BeansCrudService service;
	
	@PostConstruct
	public void initNewBeans() {
		beansBean = new BeansBean();
	}
	
	public void addButton() throws Exception {
		try {
			service.createNewBeans(mapper.toEntity(beansBean));
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initNewBeans();
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
