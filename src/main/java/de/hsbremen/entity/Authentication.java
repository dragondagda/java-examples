package de.hsbremen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authentifizierung")
public class Authentication {

	@Id
	@Column(name="typ", length=20)
	private String type;

	// Getter and Setter
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
