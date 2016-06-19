package de.hsb.jdbc.service;

import de.hsb.jdbc.persistence.MemberDao;
import de.hsb.jdbc.service.dto.MembersDto;

public class MemberService implements AutoCloseable{

	private MemberDao memberDao;

	public MemberService()  {
		memberDao = new MemberDao();
	}
	
	public MembersDto fetchMembers(){
		MembersDto membersDto = new MembersDto();
		membersDto.setCount(memberDao.countMembers());
		membersDto.setMemberList(memberDao.findAll());
		return membersDto;
	}
	
	@Override
	public void close() throws Exception {
		memberDao.close();
	}
}
