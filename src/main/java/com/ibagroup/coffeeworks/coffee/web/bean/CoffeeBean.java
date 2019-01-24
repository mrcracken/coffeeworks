package com.ibagroup.coffeeworks.coffee.web.bean;

import java.io.Serializable;

import com.ibagroup.coffeeworks.beans.web.bean.BeansBean;
/**
 * 
 * @author IBA Group
 * @since 2018
 *
 */
/*
 * We suppress the warning about not specifying a serialVersionUID, as we are still developing this app, and want the JVM to
 * generate the serialVersionUID for us. When we put this app into production, we'll generate and embed the serialVersionUID
 */
@SuppressWarnings("serial")
public class CoffeeBean implements Serializable {

	private Long id;
	private String name;
	private Integer stars;
	private String location;
	private String description;
	private BeansBean beans;
	
	public CoffeeBean() {
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getStars() {
		return stars;
	}
	
	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BeansBean getBeans() {
		return beans;
	}

	public void setBeans(BeansBean beansBean) {
		this.beans = beansBean;
	}
	
}
