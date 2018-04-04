package edu.northeastern.cs5200.orm.jpa.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5200.orm.jpa.entities.Director;
import edu.northeastern.cs5200.orm.jpa.entities.DirectorRepository;

@RestController
public class DirectorDao {
	@Autowired
	DirectorRepository directorRepository;

	@PostMapping("/api/director")
	public Director createDirector(@RequestBody Director director) {
		return directorRepository.save(director);
	}
	
	@GetMapping("/api/director")
	public Iterable<Director> findAllDirectors() {
		return directorRepository.findAll();
	}
	
	@GetMapping("/api/director/{directorId}")
	public Director findDirectorById(
			@PathVariable("directorId") int id) {
		return directorRepository.findOne(id);
	}

	@PutMapping("/api/director/{directorId}")
	public Director updateDirector(
			@PathVariable("directorId") int id,
			@RequestBody Director newDirector) {
		Director director = directorRepository.findOne(id);
		director.set(newDirector);
		return directorRepository.save(director);
	}
	
	@DeleteMapping("/api/director/{directorId}")
	public void deleteDirector
	(@PathVariable("directorId") int id) {
		directorRepository.delete(id);
	}
	
	public void test() {
		
	}
}
