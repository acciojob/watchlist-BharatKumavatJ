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
        MovieDataBase.put(movieToBeAdded.name, movieToBeAdded);
    }

    public void addDirector(Director directorToBeAdded){
        DirectorDataBase.put(directorToBeAdded.name, directorToBeAdded);
    }

    public Movie getMovieByName(String name){
        return MovieDataBase.get(name);
    }

    public void CreatePairAndAddToPairDataBase(String directorName, Movie movie){
        if(PairDataBase.containsKey((directorName)))
            PairDataBase.get(directorName).add(movie);
        else{
            List<Movie> listOfMovies = new ArrayList<>();
            listOfMovies.add(movie);
            PairDataBase.put(directorName, listOfMovies);
        }
    }

    public Director getDirectorByName(String directorName){
        return DirectorDataBase.get(directorName);
    }

    public List<Movie> getMoviesByDirectorName(String directorName){
        return PairDataBase.get(directorName);
    }

    public List<Movie> getAllMoviesFromDataBase(){
        List<Movie> listOfAllMovies = new ArrayList<>();

        for(Movie movie : MovieDataBase.values()){
            listOfAllMovies.add(movie);
        }
        return listOfAllMovies;

    }
    public  void deleteDirectorByName(String directorName){

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

    }

    public void deleteAllMoviesMappedWithDirector(){
        // I have to remove all the mapping
        // databases is like this
        // director : x -> x prop, y - y prop, z -> prop
        // movies : x -> x prop, y - y prop, z -> prop
        // Pair : director Name : movies made by director

        for(String directorName  : PairDataBase.keySet()){
            deleteDirectorByName(directorName);
        }

    }
}

