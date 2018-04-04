package edu.northeastern.cs5200.orm.jpa.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.orm.jpa.entities.Actor;
import edu.northeastern.cs5200.orm.jpa.entities.ActorRepository;

@RestController
public class ActorDao {
	@Autowired
	ActorRepository actorRepository;

	@PostMapping("/api/actor")
	public Actor createActor(@RequestBody Actor actor) {
		return actorRepository.save(actor);
	}
	
	@GetMapping("/api/actor")
	public Iterable<Actor> findAllActors() {
		return actorRepository.findAll();
	}
	
	@GetMapping("/api/actor/{actorId}")
	public Actor findActorById(
			@PathVariable("actorId") int id) {
		return actorRepository.findOne(id);
	}

	@PutMapping("/api/actor/{actorId}")
	public Actor updateActor(
			@PathVariable("actorId") int id,
			@RequestBody Actor newActor) {
		Actor actor = actorRepository.findOne(id);
		actor.set(newActor);
		return actorRepository.save(actor);
	}
	
	@DeleteMapping("/api/actor/{actorId}")
	public void deleteActor
	(@PathVariable("actorId") int id) {
		actorRepository.delete(id);
	}
	
	public void test() {
		// Delete all actors
		List<Actor> actors = (List<Actor>) findAllActors();
		for(Actor actor : actors) {
			deleteActor(actor.getId());
		}
	}
}
