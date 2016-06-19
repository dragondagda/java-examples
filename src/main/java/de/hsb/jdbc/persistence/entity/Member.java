package de.hsb.jdbc.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

	private Long memberNr;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	private String email;
	
	
	
}
