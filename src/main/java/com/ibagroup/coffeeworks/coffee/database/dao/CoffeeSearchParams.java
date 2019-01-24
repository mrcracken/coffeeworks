package com.ibagroup.coffeeworks.coffee.database.dao;

/**
 * @author IBA Group
 * @since 2019
 */
public class CoffeeSearchParams {
	
	private Long id;
	private String name;
	private String description;
	private String location;
	private Integer stars;
	private String beanName;
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public Integer getStars() {
		return stars;
	}
	
	public String getBeanName() {
		return beanName;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

}
