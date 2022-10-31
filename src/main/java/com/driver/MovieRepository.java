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

        Movie newMovie = new Movie(movieToBeAdded.name, movieToBeAdded.durationInMinutes, movieToBeAdded.imdbRating);
        MovieDataBase.put(newMovie.getName(), newMovie);

    }

    public void addDirector(Director directorToBeAdded){
        Director newDirector = new Director(directorToBeAdded.name, directorToBeAdded.numberOfMovies, directorToBeAdded.imdbRating);
        DirectorDataBase.put(directorToBeAdded.name, newDirector);
    }

    public Movie getMovieByName(String name){

        Movie movieToBeReturned = MovieDataBase.get(name);
        return movieToBeReturned;
    }

    public void CreatePairAndAddToPairDataBase(String directorName, String movieName){

        System.out.print(movieName);
        Movie movie = MovieDataBase.get(movieName);
        // System.out.print(movie.getName() + "" + movie.getDurationInMinutes() + " " + "line 44 class repo");
        if(PairDataBase.containsKey((directorName)) == true) {
            PairDataBase.get(directorName).add(movie);
        }
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

        if(PairDataBase.containsKey(directorName))
        return PairDataBase.get(directorName);
        return new ArrayList<>();
    }

    public List<Movie> getAllMoviesFromDataBase(){
        List<Movie> listOfAllMovies = new ArrayList<>();

        if(MovieDataBase.size() > 0)
        for(Movie movie : MovieDataBase.values()){
            listOfAllMovies.add(movie);
        }
        return listOfAllMovies;

    }
    public  void deleteDirectorByName(String directorName){


        List<Movie> listOfMovieMadeByDirector = null;
        // it will give me all the movies made by x director (x = directorName)
        if(PairDataBase.containsKey(directorName))
            listOfMovieMadeByDirector = PairDataBase.get(directorName);



        //  I have to remove all the movies made by x director from movie database
        if(listOfMovieMadeByDirector != null)
        for(Movie movie : listOfMovieMadeByDirector){
            MovieDataBase.remove(movie.getName());
        }

        // I have to remove Entry of x from Director DataBase
        if(DirectorDataBase.containsKey(directorName))
        DirectorDataBase.remove(directorName);


        // finally i m gonna remove pair
        if(PairDataBase.containsKey(directorName))
        PairDataBase.remove(directorName);

    }

    public void deleteAllMoviesMappedWithDirector(){
        // I have to remove all the mapping
        // databases is like this
        // director : x -> x prop, y - y prop, z -> prop
        // movies : x -> x prop, y - y prop, z -> prop
        // Pair : director Name : movies made by director

        if(PairDataBase != null)
        for(String directorName  : PairDataBase.keySet()){
            deleteDirectorByName(directorName);

        }


    }
}

