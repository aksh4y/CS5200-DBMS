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

public class MovieDao {

	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public Movie createMovie(Movie movie) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(movie);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return movie;
	}

	public List<Movie> findAllMovies() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select m from Movie m");
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) query.getResultList();
		em.getTransaction().commit();
		em.close();		
		return movies;
	}

	public Movie findMovieById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		em.getTransaction().commit();
		em.close();
		return movie;
	}

	public Movie findMovieByTitle(String title) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select m from Movie m where m.title = :title");
		query.setParameter("title", title);
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) query.getResultList();
		em.getTransaction().commit();
		em.close();		
		if(movies.size() == 0)
			return null;
		return movies.get(0);
	}

	public Movie updateMovie (int id, Movie newMovie) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		for(Actor actor : movie.getActors()) {
			actor = em.find(Actor.class, actor.getId());
			actor.addMoviesActed(movie);
		}
		for(Director director : movie.getDirectors()) {
			director = em.find(Director.class, director.getId());
			director.addMoviesDirected(movie);
		}
		movie.set(newMovie);
		em.getTransaction().commit();
		em.close();
		return movie;
	}

	public void deleteMovie (int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		if(movie != null)
			em.remove(movie);
		em.getTransaction().commit();
		em.close();
	}

	public void addActorToMovie(int mId, int aId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, mId);
		Actor actor = em.find(Actor.class, aId);
		actor.getMoviesActed().add(movie);
		em.getTransaction().commit();
		em.close();
	}


	public void test() {
		/**********************(a)**********************/

		List<Movie> movies = findAllMovies();
		if(movies.size() > 0)
			for(Movie movie: movies)
				deleteMovie(movie.getId());

		/**********************(b)**********************/

		List<Actor> actors = new ArrayList<Actor>();
		List<Director> directors = new ArrayList<Director>();
		Movie interim;

		Actor harrison = new Actor();
		harrison.setFirstName("Harrison");
		harrison.setLastName("Ford");
		actors.add(harrison);

		Actor rutger = new Actor();
		rutger.setFirstName("Rutger");
		rutger.setLastName("Hauer");
		actors.add(rutger);

		Director ridley = new Director();
		ridley.setFirstName("Ridley");
		ridley.setLastName("Scott");
		directors.add(ridley);

		Movie bladeRunner = new Movie();
		bladeRunner.setTitle("Blade Runner");
		bladeRunner.setActors(actors);
		bladeRunner.setDirectors(directors);
		interim = createMovie(bladeRunner);
		bladeRunner = interim;

		actors.clear();	// Reuse lists for subsequent operations
		directors.clear();

		/**********************(c)**********************/

		ActorDao aDao = new ActorDao();
		Actor a = aDao.findActorByName("Harrison", "Ford");
		actors.add(a);

		Actor karen = new Actor();
		karen.setFirstName("Karen");
		karen.setLastName("Allen");
		actors.add(karen);

		Director steven = new Director();
		steven.setFirstName("Steven");
		steven.setLastName("Spielberg");
		directors.add(steven);

		Movie raiders = new Movie();
		raiders.setTitle("Raiders of The Lost Ark");
		raiders.setActors(actors);
		raiders.setDirectors(directors);
		interim = createMovie(raiders);
		raiders = interim;
		actors.clear();
		directors.clear();

		/**********************(d)**********************/

		Actor richard = new Actor();
		richard.setFirstName("Richard");
		richard.setLastName("Dreyfus");
		actors.add(richard);

		Actor melinda = new Actor();
		melinda.setFirstName("Melinda");
		melinda.setLastName("Dillon");
		actors.add(melinda);

		DirectorDao directorDao = new DirectorDao();
		Director d = directorDao.findDirectorByName("Steven", "Spielberg");
		directors.add(d);

		Movie close = new Movie();
		close.setTitle("Close Encounters of the Third Kind");
		close.setActors(actors);
		close.setDirectors(directors);
		interim = createMovie(close);
		close = interim;

		actors.clear();
		directors.clear();


		/**********************(e)**********************/

		movies = findAllMovies();
		for(Movie movie : movies) {
			System.out.println(movie.getTitle());
			for(Actor actor : movie.getActors())
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			for(Director director: movie.getDirectors())
				System.out.println(director.getFirstName() + " " + director.getLastName());
		}

		/**********************(f)**********************/

		ActorDao actorDao = new ActorDao();
		Actor actor = actorDao.findActorByName("Harrison", "Ford");
		System.out.println(actor.getFirstName() + " " + actor.getLastName());
		for(Movie movie : actor.getMoviesActed())
			System.out.println(movie.getTitle());

		/**********************(g)**********************/

		Director director = directorDao.findDirectorByName("Steven", "Spielberg");
		System.out.println(director.getFirstName() + " " + director.getLastName());
		for(Movie movie : director.getMoviesDirected())
			System.out.println(movie.getTitle());
	}
}
