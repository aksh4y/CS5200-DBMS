package edu.northeastern.cs5200.web.services.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.northeastern.cs5200.orm.jpa.daos.MovieDao;
import edu.northeastern.cs5200.orm.jpa.entities.Actor;
import edu.northeastern.cs5200.orm.jpa.entities.Movie;


@Path("movie")
public class MovieService {

	MovieDao dao = new MovieDao();
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> findAllMovies() {
		return dao.findAllMovies();
	}

	@Path("/{mid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Movie findMovieById(@PathParam("mid") int id) {
		return dao.findMovieById(id);
	}

	@Path("/{mid}/actor")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> findMoviesByActorId(@PathParam("mid") int id) {
		return dao.findMovieById(id).getActors();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Movie createMovie(Movie movie) {
		Movie m = dao.createMovie(movie);
		return m;
	}

	@Path("/{mid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Movie updateMovie(@PathParam("mid") int id, Movie newMovie) {
		Movie movie = dao.updateMovie(id, newMovie);
		return movie;
	}

	@Path("/{mid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteActor(@PathParam("mid") int id) {
		try {
			dao.deleteMovie(id);
		}
		catch(Exception e) {
			return "0";
		}
		return "1";
	}
}
