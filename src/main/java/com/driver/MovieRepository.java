package com.driver;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//in this file i will write my database related things
// i want to use IOC Container in my project
@Component
public class MovieRepository {

    // @Componenet class must have default constructor
    public MovieRepository(){}

    // databases
    private HashMap<String, Movie> MovieDataBase = new HashMap<>(); //movieName : Movie property(name, rating, duration)
    private HashMap<String, Director> DirectorDataBase = new HashMap<>(); // directorName : Director Property(name, ratting, numOfMov)
    private HashMap<String, List<Movie>> PairDataBase = new HashMap<>(); // DirectorName : movies

    public void addMovie(Movie movieToBeAdded){

        try{

            Movie newMovie = new Movie(movieToBeAdded.name, movieToBeAdded.durationInMinutes, movieToBeAdded.imdbRating);
            MovieDataBase.put(newMovie.getName(), newMovie);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void addDirector(Director directorToBeAdded){
        try {
            Director newDirector = new Director(directorToBeAdded.name, directorToBeAdded.numberOfMovies, directorToBeAdded.imdbRating);
            DirectorDataBase.put(directorToBeAdded.name, newDirector);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Movie getMovieByName(String name) throws Exception {

        try {
            Movie movieToBeReturned = MovieDataBase.get(name);
            return movieToBeReturned;
        } catch (Exception e) {
            throw e;
        }
    }

    public void CreatePairAndAddToPairDataBase(String directorName, String movieName){

        try {
            Movie movie = MovieDataBase.get(movieName);
            // System.out.print(movie.getName() + "" + movie.getDurationInMinutes() + " " + "line 44 class repo");
            if (PairDataBase.containsKey((directorName)) == true) {
                PairDataBase.get(directorName).add(movie);
            } else {

                List<Movie> listOfMovies = new ArrayList<>();
                listOfMovies.add(movie);
                PairDataBase.put(directorName, listOfMovies);

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Director getDirectorByName(String directorName) throws Exception {
        try {
            return DirectorDataBase.get(directorName);
        }catch (Exception e){
            throw e;
        }

    }

    public List<Movie> getMoviesByDirectorName(String directorName) throws Exception{

        try{
            return PairDataBase.get(directorName);

        }catch (Exception e){
            throw e;
        }
    }

    public List<Movie> getAllMoviesFromDataBase() throws Exception{

        try {
            List<Movie> listOfAllMovies = new ArrayList<>();

            for (Movie movie : MovieDataBase.values()) {
                listOfAllMovies.add(movie);
            }
            return listOfAllMovies;
        }catch (Exception e){
            throw e;
        }
    }
    public  void deleteDirectorByName(String directorName){

        try{
            // it will give me all the movies made by x director (x = directorName)
            List<Movie> listOfMovieMadeByDirector = PairDataBase.get(directorName);

            //  I have to remove all the movies made by x director from movie database
            for(Movie movie : listOfMovieMadeByDirector){
                MovieDataBase.remove(movie.getName());
            }

            // I have to remove Entry of x from Director DataBase

            DirectorDataBase.remove(directorName);


            // finally i m gonna remove pair

            PairDataBase.remove(directorName);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void deleteAllMoviesMappedWithDirector(){
        // I have to remove all the mapping
        // databases is like this
        // director : x -> x prop, y - y prop, z -> prop
        // movies : x -> x prop, y - y prop, z -> prop
        // Pair : director Name : movies made by director
        try{
            for(String directorName  : PairDataBase.keySet()){
                deleteDirectorByName(directorName);
            }
        }catch (Exception e){
            System.out.print(e);
        }

    }
}

