package edu.northeastern.cs5200.orm.jpa.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Actor extends Person {
	private int oscarNominations;
	
	@ManyToMany(mappedBy = "actors")
	@JsonIgnore
	private List<Movie> moviesActed;
	
	public Actor() {
		super();
	}

	public int getOscarNominations() {
		return oscarNominations;
	}

	public void setOscarNominations(int oscarNominations) {
		this.oscarNominations = oscarNominations;
	}

	public List<Movie> getMoviesActed() {
		return moviesActed;
	}

	public void setMoviesActed(List<Movie> moviesActed) {
		this.moviesActed = moviesActed;
	}

	public void set(Actor newActor) {
		this.oscarNominations = newActor.oscarNominations != 0 ?
				newActor.oscarNominations : this.oscarNominations; 
		this.moviesActed = newActor.moviesActed != null ?
				newActor.moviesActed : this.moviesActed; 
	}
 
}
