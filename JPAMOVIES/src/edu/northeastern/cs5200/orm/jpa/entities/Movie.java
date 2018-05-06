package edu.northeastern.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Entity implementation class for Entity: Movie
 *
 */
@XmlRootElement
@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private MovieLibrary library;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Movie2Actor",
	joinColumns=@JoinColumn(name="MOVIE_ID", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="ACTOR_ID", referencedColumnName="id"))
	@JsonIgnore
	private List<Actor> actors = null;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Movie2Director",
	joinColumns=@JoinColumn(name="MOVIE_ID", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="DIRECTOR_ID", referencedColumnName="id"))
	@JsonIgnore
	private List<Director> directors = null;

	public Movie() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonIgnore
	public MovieLibrary getLibrary() {
		return library;
	}
	
	public void setLibrary(MovieLibrary library) {
		this.library = library;
		if(!library.getMovies().contains(this))
			library.getMovies().add(this);
	}
	@JsonIgnore
	public List<Actor> getActors() {
		if (actors == null)
			actors = new ArrayList<Actor>();
		return actors;
	}
	public void setActors(List<Actor> actors) {
		for(Actor actor: actors) {
			if(!this.getActors().contains(actor))
				this.getActors().add(actor);
			if(!actor.getMoviesActed().contains(this))
				actor.getMoviesActed().add(this);
		}
	}
	public void addActor(Actor actor) {
		this.actors.add(actor);
		if(!actor.getMoviesActed().contains(this))
			actor.getMoviesActed().add(this);
	}
	@JsonIgnore
	public List<Director> getDirectors() {
		if(directors == null)
			directors = new ArrayList<Director>();
		return directors;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
		for(Director director: directors) {
			if(!this.getDirectors().contains(director))
				this.getDirectors().add(director);
			if(!director.getMoviesDirected().contains(this))
				director.getMoviesDirected().add(this);
		}
	}
	public void set(Movie newMovie) {
		this.title = newMovie.title != null ?
				newMovie.title : this.title; 
		this.library = newMovie.library != null ?
				newMovie.library : this.library;
		this.actors = newMovie.actors != null ?
				newMovie.actors : this.actors;
		this.directors = newMovie.directors != null ?
				newMovie.directors : this.directors;
	}
}
