package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
// in this class i will write business  logic
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    public void addMovie(Movie movieToBeAdded){
        movieRepository.addMovie(movieToBeAdded);
    }

    public void addDirector(Director directorToBeAdded){
        movieRepository.addDirector(directorToBeAdded);
    }

    public void addMovieDirectorPair(String directorName, String movieName){

        movieRepository.CreatePairAndAddToPairDataBase(directorName, movieName);

    }

    public Movie getMovieByName(String movieName){
        Movie movie = movieRepository.getMovieByName(movieName);
        return movie;
    }

    public Director getDirector(String directorName){
        Director director = movieRepository.getDirectorByName(directorName);
        return director;
    }

    public List<Movie> getListOfMoviesMadeByDirector(String directorName){
        List<Movie> listOfMoviesMadeByDirector = movieRepository.getMoviesByDirectorName(directorName);
        return listOfMoviesMadeByDirector;
    }

    public List<Movie> getAllMovies(){

        return movieRepository.getAllMoviesFromDataBase();
    }

    public void deleteDirectorByName(String directorName){
        movieRepository.deleteDirectorByName(directorName);
    }

    public void deleteAllMoviesMappedWithDirector(){
        movieRepository.deleteAllMoviesMappedWithDirector();
    }
}
