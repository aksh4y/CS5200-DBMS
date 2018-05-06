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

import edu.northeastern.cs5200.orm.jpa.daos.ActorDao;
import edu.northeastern.cs5200.orm.jpa.entities.Actor;
import edu.northeastern.cs5200.orm.jpa.entities.Movie;

@Path("actor")
public class ActorService {

	ActorDao dao = new ActorDao();
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> findAllActors() {
		return dao.findAllActors();
	}

	@Path("/{aid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Actor findActorById(@PathParam("aid") int id) {
		return dao.findActorById(id);
	}

	@Path("/{aid}/movie")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> findMoviesByActorId(@PathParam("aid") int id) {
		return dao.findActorById(id).getMoviesActed();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Actor createActor(Actor actor) {
		Actor a = dao.createActor(actor);
		return a;
	}

	@Path("/{aid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Actor updateActor(@PathParam("aid") int id, Actor newActor) {
		Actor actor = dao.updateActor(id, newActor);
		return actor;
	}

	@Path("/{aid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteActor(@PathParam("aid") int id) {
		try {
			dao.deleteActor(id);
		}
		catch(Exception e) {
			return "0";
		}
		return "1";
	}

}
