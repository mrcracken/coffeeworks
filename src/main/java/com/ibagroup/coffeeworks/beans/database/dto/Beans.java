package com.ibagroup.coffeeworks.beans.database.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * We suppress the warning about not specifying a serialVersionUID, as we are still developing this app, and want the JVM to
 * generate the serialVersionUID for us. When we put this app into production, we'll generate and embed the serialVersionUID
 */
@SuppressWarnings("serial")
@Entity
public class Beans implements Serializable {

	/* Declaration of fields */

    /**
     * The synthetic id of the object.
     */
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	public Beans() {
		
	}
	
	/* Constructor with arguments we gonna use in DAO */
	public Beans(Long id, String name) {
        this.id = id;
        this.name = name;
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
