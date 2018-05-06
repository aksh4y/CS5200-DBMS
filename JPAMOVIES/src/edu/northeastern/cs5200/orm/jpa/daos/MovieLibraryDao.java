package edu.northeastern.cs5200.orm.jpa.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.northeastern.cs5200.orm.jpa.entities.Actor;
import edu.northeastern.cs5200.orm.jpa.entities.Director;
import edu.northeastern.cs5200.orm.jpa.entities.Movie;
import edu.northeastern.cs5200.orm.jpa.entities.MovieLibrary;

public class MovieLibraryDao {

	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public MovieLibrary createMovieLibrary(MovieLibrary library) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(library);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return library;
	}

	public List<MovieLibrary> findAllMovieLibraries() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select m from MovieLibrary m");
		@SuppressWarnings("unchecked")
		List<MovieLibrary> libraries = (List<MovieLibrary>) query.getResultList();
		em.getTransaction().commit();
		em.close();		
		return libraries;
	}

	public MovieLibrary findMovieLibraryById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MovieLibrary library = em.find(MovieLibrary.class, id);
		em.getTransaction().commit();
		em.close();
		return library;
	}

	public MovieLibrary findMovieLibraryByName(String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select m from MovieLibrary m where m.name = :name");
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<MovieLibrary> libraries = (List<MovieLibrary>) query.getResultList();
		em.getTransaction().commit();
		em.close();		
		if(libraries.size() == 0)
			return null;
		return libraries.get(0);
	}

	public MovieLibrary updateMovieLibrary (int id, MovieLibrary newLibrary) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MovieLibrary library = em.find(MovieLibrary.class, id);
		library.set(newLibrary);
		em.getTransaction().commit();
		em.close();
		return library;
	}

	public void deleteMovieLibrary (int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		MovieLibrary library = em.find(MovieLibrary.class, id);
		if(library != null)
			em.remove(library);
		em.getTransaction().commit();
		em.close();
	}



	public void test() {
		/**********************(a)**********************/

		List<MovieLibrary> libraries = findAllMovieLibraries();
		for(MovieLibrary library : libraries)
			deleteMovieLibrary(library.getId());

		/**********************(b)**********************/

		
		MovieLibrary favMovies = new MovieLibrary();
		favMovies.setName("Favorite Movies");
		
		List<Actor> actors = new ArrayList<Actor>();
		List<Director> directors = new ArrayList<Director>();
		List<Director> directors2 = new ArrayList<Director>();
		List<Movie> movies = new ArrayList<Movie>();
				
		Actor mark = new Actor();
		mark.setFirstName("Mark");
		mark.setLastName("Hamill");
		actors.add(mark);
		
		Actor carrie = new Actor();
		carrie.setFirstName("Carrie");
		carrie.setLastName("Fisher");
		
		actors.add(carrie);
		
		Director george = new Director();
		george.setFirstName("George");
		george.setLastName("Lucas");
		
		directors.add(george);
		
		Movie starWars = new Movie();
		starWars.setTitle("Star Wars A New Hope");
		starWars.setActors(actors);
		starWars.setDirectors(directors);
		movies.add(starWars);
		
		actors.clear();
		
		Actor leo = new Actor();
		leo.setFirstName("Leonardo");
		leo.setLastName("DiCaprio");
		
		Actor tom = new Actor();
		tom.setFirstName("Tom");
		tom.setLastName("Hardy");
		
		actors.add(leo);
		actors.add(tom);
		
		Director alejandro = new Director();
		alejandro.setFirstName("Alejandro");
		alejandro.setLastName("Inarritu");
		
		directors2.add(alejandro);
		
		Movie revnant = new Movie();
		revnant.setTitle("The Revanant");
		revnant.setActors(actors);
		revnant.setDirectors(directors2);
		movies.add(revnant);
		
		favMovies.setMovies(movies);
		createMovieLibrary(favMovies);

		
		/**********************(c)**********************/
		
		MovieLibrary library = findMovieLibraryByName("Favorite Movies");
		System.out.println(library.getName());
		for(Movie m : library.getMovies()) {
			System.out.println(m.getTitle());
			for(Actor a : m.getActors())
				System.out.println(a.getFirstName() + " " + a.getLastName());
			for(Director d : m.getDirectors())
				System.out.println(d.getFirstName() + " " + d.getLastName());
		}
	}
}
