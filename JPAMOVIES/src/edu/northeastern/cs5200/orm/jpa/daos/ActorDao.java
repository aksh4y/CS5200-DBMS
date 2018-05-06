package edu.northeastern.cs5200.orm.jpa.daos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.northeastern.cs5200.orm.jpa.entities.Actor;
import edu.northeastern.cs5200.orm.jpa.entities.Person;

public class ActorDao {

	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public Actor createActor(Actor actor) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(actor);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return actor;
	}

	public List<Actor> findAllActors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select a from Actor a");
		@SuppressWarnings("unchecked")
		List<Actor> actors = (List<Actor>) query.getResultList();
		em.getTransaction().commit();
		em.close();		
		return actors;
	}

	public Actor findActorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = em.find(Actor.class, id);
		em.getTransaction().commit();
		em.close();
		return actor;
	}

	public Actor findActorByName(String firstName, String lastName) {
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
		return findActorById(person.get(0).getId());
	}

	public Actor updateActor (int id, Actor newActor) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = em.find(Actor.class, id);
		actor.set(newActor);
		em.getTransaction().commit();
		em.close();
		return actor;
	}

	public void deleteActor (int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = em.find(Actor.class, id);
		if(actor != null)
			em.remove(actor);
		em.getTransaction().commit();
		em.close();
	}


	public void test() {
		/**********************(a)**********************/
		List<Actor> actors = findAllActors();
		for(Actor actor: actors)
			deleteActor(actor.getId());
		//System.out.println("Completed (a)...");

		/**********************(b)**********************/

		Actor sigorney = new Actor();
		sigorney.setFirstName("Sigorney");
		sigorney.setLastName("Weaver");
		int sigorneyId = createActor(sigorney).getId();

		Actor dan = new Actor();
		dan.setFirstName("Dan");
		dan.setLastName("Creg");
		int danId = createActor(dan).getId();

		Actor jim = new Actor();
		jim.setFirstName("Jim");
		jim.setLastName("Carrey");
		int jimId = createActor(jim).getId();

		/**********************(c)**********************/

		Actor actor1 = findActorById(sigorneyId);	// Can't use findActorById(1) as 1 may not always exist after server restart
		System.out.println(actor1.getFirstName() + " " + actor1.getLastName());

		/**********************(d)**********************/

		actors = findAllActors();
		for(Actor actor : actors)
			System.out.println(actor.getFirstName() + " " + actor.getLastName());

		/**********************(e)**********************/

		Actor daniel = new Actor();
		daniel.setFirstName("Daniel");
		daniel.setLastName("Craig");
		updateActor(danId, daniel);

		/**********************(f)**********************/

		deleteActor(jimId);

		/**********************(g)**********************/

		actors = findAllActors();
		for(Actor actor : actors)
			System.out.println(actor.getFirstName() + " " + actor.getLastName());

	}
}
