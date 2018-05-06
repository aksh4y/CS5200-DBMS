package edu.northeastern.cs5200.orm.jpa.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.northeastern.cs5200.orm.jpa.entities.Director;
import edu.northeastern.cs5200.orm.jpa.entities.Person;

public class DirectorDao {

	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public Director createDirector(Director director) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(director);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return director;
	}

	public List<Director> findAllDirectors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select a from Director a");
		@SuppressWarnings("unchecked")
		List<Director> directors = (List<Director>) query.getResultList();
		em.getTransaction().commit();
		em.close();		
		return directors;
	}

	public Director findDirectorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Director director = em.find(Director.class, id);
		em.getTransaction().commit();
		em.close();
		return director;
	}

	public Director findDirectorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select p from Person p where p.firstName=:firstName and p.lastName=:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		@SuppressWarnings("unchecked")
		List<Person> person = query.getResultList();
		em.getTransaction().commit();
		em.close();
		if(person.size() == 0)
			return null;
		return findDirectorById(person.get(0).getId());
	}

	public void updateDirector (int id, Director newDirector) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Director director = em.find(Director.class, id);
		director.set(newDirector);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteDirector (int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Director director = em.find(Director.class, id);
		if(director != null)
			em.remove(director);
		em.getTransaction().commit();
		em.close();
	}


	public void test() {
		/**********************(a)**********************/
		List<Director> directors = findAllDirectors();
		for(Director director: directors)
			deleteDirector(director.getId());

		/**********************(b)**********************/

		Director jimmy = new Director();
		jimmy.setFirstName("Jimmy");
		jimmy.setLastName("Camaron");
		int jimmyId = createDirector(jimmy).getId();

		Director steven = new Director();
		steven.setFirstName("Steven");
		steven.setLastName("Spielberg");
		createDirector(steven);

		Director ron = new Director();
		ron.setFirstName("Ron");
		ron.setLastName("Howard");
		int ronId = createDirector(ron).getId();

		/**********************(c)**********************/

		// Can't use findDirectorById(1) as ID 1 may not always exist after server restart
		// and not returning the first object of findAllDirectors as it is not guaranteed to be in order
		// plus jimmyId was returned after insertion
		Director director1 = findDirectorById(jimmyId);	
		System.out.println(director1.getFirstName() + " " + director1.getLastName());

		/**********************(d)**********************/

		directors = findAllDirectors();
		for(Director director : directors)
			System.out.println(director.getFirstName() + " " + director.getLastName());

		/**********************(e)**********************/

		Director james = new Director();
		james.setFirstName("James");
		james.setLastName("Cameron");
		updateDirector(jimmyId, james);

		/**********************(f)**********************/

		deleteDirector(ronId);

		/**********************(g)**********************/

		directors = findAllDirectors();
		for(Director director : directors)
			System.out.println(director.getFirstName() + " " + director.getLastName());
	}



}
