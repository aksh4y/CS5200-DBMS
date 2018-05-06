package edu.northeastern.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Entity implementation class for Entity: Actor
 *
 */
@XmlRootElement
@Entity
public class Actor extends Person implements Serializable {


	private static final long serialVersionUID = 1L;

	private int oscarNominations;
	@ManyToMany(mappedBy = "actors", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Movie> moviesActed = null;

	public Actor() {
		super();
	}

	public void addMoviesActed(Movie movie) {
		if(!this.getMoviesActed().contains(movie))
			this.getMoviesActed().add(movie);
	}

	public int getOscarNominations() {
		return oscarNominations;
	}

	public void setOscarNominations(int oscarNominations) {
		this.oscarNominations = oscarNominations;
	}

	public List<Movie> getMoviesActed() {
		if(moviesActed == null)
			moviesActed = new ArrayList<Movie>();
		return moviesActed;
	}

	public void setMoviesActed(List<Movie> moviesActed) {
		for(Movie movie: moviesActed) {
			if(!this.getMoviesActed().contains(movie))
				this.getMoviesActed().add(movie);
		}
	}

	public void set(Actor newActor) {
		this.setFirstName(newActor.getFirstName() != null ? newActor.getFirstName() : this.getFirstName());
		this.setLastName(newActor.getLastName() != null ? newActor.getLastName() : this.getLastName());
		this.oscarNominations = newActor.oscarNominations != 0 ?
				newActor.oscarNominations : this.oscarNominations; 
		this.moviesActed = newActor.moviesActed != null ?
				newActor.moviesActed : this.moviesActed; 
	}
}

