package de.hsbremen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mitglied")
public class Member {

	@Id
	@Column(name="mitglieds_nr")
	private Integer memberNumber;
	
	// Unique
	@Column(nullable=false)
	private String username;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="authentifizierung")
	private Authentication authentication;

	public Integer getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	
}
