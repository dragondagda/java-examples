package de.hsb.jdbc;

import java.util.List;

import de.hsb.jdbc.persistence.MemberDao;
import de.hsb.jdbc.persistence.entity.Member;

public class JdbcMain {

	@Deprecated
	public static void main(String[] args) {
		try (MemberDao memberDao = new MemberDao()) {

			System.out.println("There are " + memberDao.countMembers() + 
					" members in the database:");

			List<Member> members = memberDao.findAll();

			if (members != null) {
				members.forEach(member -> System.out.println("- " + member.toString()));
			}
		} catch (Exception e) {
			System.err.println("An exception occured (" + e.getClass().getSimpleName() + "): " 
					+ e.getMessage());
		}
	}
}
