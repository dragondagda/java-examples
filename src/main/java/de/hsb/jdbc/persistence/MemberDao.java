package de.hsb.jdbc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsb.jdbc.persistence.entity.Member;

public class MemberDao implements AutoCloseable {

	private PreparedStatement memberCountStatement;
	private PreparedStatement allMemberStatement;

	public MemberDao() {
		Connection connection = DatabaseConnection.getInstance();

		try {
			memberCountStatement = connection.prepareStatement("SELECT COUNT(*) FROM mitglied");
			allMemberStatement = connection.prepareStatement("SELECT * FROM mitglied");
		} catch (SQLException e) {
			System.err.println("SQLException occured while initializing Statements: " + e.getMessage());
		}
	}

	public int countMembers() {
		int memberCount = 0;
		try {
			ResultSet result = memberCountStatement.executeQuery();
			if (result.next()) {
				memberCount = result.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("SQLException occured in countMembers: " + e.getMessage());
		}
		return memberCount;
	}

	public List<Member> findAll() {
		final List<Member> memberList = new ArrayList<>();

		try {
			ResultSet resultSet = allMemberStatement.executeQuery();
			while (resultSet.next()) {
				Member member = new Member();
				member.setMemberNr(resultSet.getLong(1));
				member.setUsername(resultSet.getString(2));
				member.setPassword(resultSet.getString(3));
				member.setEmail(resultSet.getString(4));
				memberList.add(member);
			}
		} catch (SQLException e) {
			System.err.println("SQLException occured in findAll: " + e.getMessage());
		}
		return memberList;
	}

	@Override
	public void close() throws Exception {
		memberCountStatement.close();
		allMemberStatement.close();
	}
}
