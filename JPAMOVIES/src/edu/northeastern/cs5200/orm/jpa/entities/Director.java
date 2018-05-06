package edu.northeastern.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Director
 *
 */
@Entity
public class Director extends Person implements Serializable {


	private static final long serialVersionUID = 1L;

	private int oscarWins;
	@ManyToMany(mappedBy="directors", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Movie> moviesDirected = null;

	public Director() {
		super();
	}
	public int getOscarWins() {
		return oscarWins;
	}
	public void setOscarWins(int oscarWins) {
		this.oscarWins = oscarWins;
	}
	public List<Movie> getMoviesDirected() {
		if (moviesDirected == null)
			moviesDirected = new ArrayList<Movie>();
		return moviesDirected;
	}
	public void addMoviesDirected(Movie movie) {
		if(!this.getMoviesDirected().contains(movie))
			this.getMoviesDirected().add(movie);
	}
	public void setMoviesDirected(List<Movie> moviesDirected) {
		for(Movie movie: moviesDirected)
			if(!this.getMoviesDirected().contains(movie))
				this.getMoviesDirected().add(movie);
	}

}
