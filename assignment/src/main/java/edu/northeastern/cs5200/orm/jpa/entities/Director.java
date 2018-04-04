package edu.northeastern.cs5200.orm.jpa.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Director extends Person {

	private int oscarWins;
	@ManyToMany(mappedBy="directors")
	@JsonIgnore
	private List<Movie> moviesDirected;
	
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
		return moviesDirected;
	}
	public void setMoviesDirected(List<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
	}
}
