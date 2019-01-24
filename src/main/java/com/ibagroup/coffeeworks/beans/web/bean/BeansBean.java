package com.ibagroup.coffeeworks.beans.web.bean;

import java.io.Serializable;

/**
 * 
 * @author IBA Group
 * @since 2019
 *
 */
/*
 * We suppress the warning about not specifying a serialVersionUID, as we are still developing this app, and want the JVM to
 * generate the serialVersionUID for us. When we put this app into production, we'll generate and embed the serialVersionUID
 */
@SuppressWarnings("serial")
public class BeansBean implements Serializable {

	private Long id;
	private String name;
	
	public BeansBean() {
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
