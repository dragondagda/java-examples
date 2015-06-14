package de.hsbremen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.hsbremen.entity.Member;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceUnit");

		EntityManager em = emf.createEntityManager();

		Member member = em.find(Member.class, 2);

		System.out.println("member: " + member.getUsername() + "; auth durch: "
				+ member.getAuthentication().getType());
	}
}