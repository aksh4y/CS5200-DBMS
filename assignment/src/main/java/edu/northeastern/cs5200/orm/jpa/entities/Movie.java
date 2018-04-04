package edu.northeastern.cs5200.orm.jpa.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne()
	@JsonIgnore
	private MovieLibrary library;
	
	@ManyToMany()
	@JoinTable(name="Movie2Actor",
	joinColumns=@JoinColumn(name="MOVIE_ID", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="ACTOR_ID", referencedColumnName="id"))
	@JsonIgnore
	private List<Actor> actors;
	
	@ManyToMany()
	@JoinTable(name="Movie2Director",
	joinColumns=@JoinColumn(name="MOVIE_ID", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="DIRECTOR_ID", referencedColumnName="id"))
	@JsonIgnore
	private List<Director> directors;
	
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
	public MovieLibrary getLibrary() {
		return library;
	}
	public void setLibrary(MovieLibrary library) {
		this.library = library;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	public List<Director> getDirectors() {
		return directors;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}
	

}
