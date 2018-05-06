package edu.northeastern.cs5200.orm.jpa.daos;
import edu.northeastern.cs5200.orm.jpa.daos.*;

public class TestDao {

	public static void main(String[] args) {
		
		ActorDao actorDao = new ActorDao();
		actorDao.test();
		
		System.out.println("**********************************");
		
		DirectorDao directorDao = new DirectorDao();
		directorDao.test();
		
		System.out.println("**********************************");
		
		MovieDao movieDao = new MovieDao();
		movieDao.test();
		
		System.out.println("**********************************");
		
		MovieLibraryDao movieLibraryDao = new MovieLibraryDao();
		movieLibraryDao.test();
	}

}
