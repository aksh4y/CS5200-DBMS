package edu.northeastern.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Entity implementation class for Entity: MovieLibrary
 *
 */
@Entity

public class MovieLibrary implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "library", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Movie> movies = null;
	
	public MovieLibrary() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Movie> getMovies() {
		if(movies == null)
			movies = new ArrayList<Movie>();
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		for(Movie movie : movies) {
			if(!this.getMovies().contains(movie))
				this.getMovies().add(movie);
			if(movie.getLibrary() != this)
				movie.setLibrary(this);
		}
	}
	public void set(MovieLibrary newLibrary) {
		this.name = newLibrary.name != null ?
				newLibrary.name : this.name; 
		this.movies = newLibrary.movies != null ?
				newLibrary.movies : this.movies;
	}
   
}
