package de.hsbremen;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.hsbremen.entity.Member;
import de.hsbremen.repository.MemberRepository;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) {
		final MemberRepository memberRepository = initMemberRepository();
		
		List<Member> list = memberRepository.findMembersWithAuthentication();
		for(final Member member : list){
			System.out.println(member.getUsername());
		}
	}

	private static MemberRepository initMemberRepository() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceUnit");

		EntityManager em = emf.createEntityManager();

		final MemberRepository memberRepository = new MemberRepository(em);
		return memberRepository;
	}
}