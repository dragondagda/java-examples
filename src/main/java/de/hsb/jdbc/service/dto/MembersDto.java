package de.hsb.jdbc.service.dto;

import java.util.List;

import de.hsb.jdbc.persistence.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembersDto {

	private int count;
	
	private List<Member> memberList;
}
