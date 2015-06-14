package de.hsbremen.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hsbremen.entity.Member;

public class MemberRepository {

	private EntityManager entityManager;
	
	public MemberRepository(final EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Member> findMembersWithAuthentication(){
		Query query = this.entityManager.createQuery("SELECT m FROM Member m "
				+ "WHERE m.authentication IS NULL");
		return query.getResultList();
	}
}
