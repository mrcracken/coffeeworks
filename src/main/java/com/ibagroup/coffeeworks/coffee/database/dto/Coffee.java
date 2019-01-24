package com.ibagroup.coffeeworks.coffee.database.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibagroup.coffeeworks.beans.database.dto.Beans;

/**
 * 
 * An Entity for Coffee
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
@XmlRootElement
@Entity
public class Coffee implements Serializable {

	/* Declaration of fields */

    /**
     * The synthetic id of the object.
     */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
    * <p>
    * The name of coffee
    * </p>
    * 
    * <p>
    * The show of which this is a airport is required, and the Bean Validation constraint <code>@NotNull</code> enforces
    * this.
    * </p>
    */
	@NotNull
	private String name;
	
	/**
	* <p>
	* Coffee chart
	* </p>
	*/
	private Integer stars;
	
	@ManyToOne
	private Beans beans;
	
	/**
	* <p>
	* The location where coffee were born
	* </p>
	* 
	* <p>
	* The show of which this is a airport is required, and the Bean Validation constraint <code>@NotNull</code> enforces
	* this.
	* </p>
	*/
	@NotNull
	private String location;
	
	/**
	* <p>
	* Some words about coffee
	* </p>
	*/
	private String description;
	
	public Coffee() {
		
	}
	
	/* Constructor with arguments we gonna use in DAO */
	public Coffee(Long id, String name, String location, String description,Beans beans, Integer stars) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.beans = beans;
        this.stars = stars;
    }

	
	/* Boilerplate getters and setters */
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStars() {
		return stars;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}
	
	public Beans getBeans() {
		return beans;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBeans(Beans beans) {
		this.beans = beans;
	}

	/* equals() and hashCode() for Performance, using the natural identity of the object */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beans == null) ? 0 : beans.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((stars == null) ? 0 : stars.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coffee other = (Coffee) obj;
		if (beans == null) {
			if (other.beans != null)
				return false;
		} else if (!beans.equals(other.beans))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stars == null) {
			if (other.stars != null)
				return false;
		} else if (!stars.equals(other.stars))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coffee [id=" + id + ", name=" + name + ", stars=" + stars + ", beans=" + beans + ", location="
				+ location + ", description=" + description + "]";
	}
	
}
