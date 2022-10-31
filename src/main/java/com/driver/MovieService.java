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
        try{
            movieRepository.addMovie(movieToBeAdded);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void addDirector(Director directorToBeAdded) {
        try {
            movieRepository.addDirector(directorToBeAdded);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void addMovieDirectorPair(String directorName, String movieName){

        try{
            movieRepository.CreatePairAndAddToPairDataBase(directorName, movieName);
        }catch (Exception e){
            System.out.println(e);
        }


    }

    public Movie getMovieByName(String movieName) throws Exception {
        try {
            Movie movie = movieRepository.getMovieByName(movieName);
            return movie;
        }catch (Exception e){
            throw e;
        }
    }

    public Director getDirector(String directorName) throws Exception {
        try {
            Director director = movieRepository.getDirectorByName(directorName);
            return director;
        } catch (Exception e){
            throw e;
        }
    }

    public List<Movie> getListOfMoviesMadeByDirector(String directorName) throws Exception {
        try {


            List<Movie> listOfMoviesMadeByDirector = movieRepository.getMoviesByDirectorName(directorName);
            return listOfMoviesMadeByDirector;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Movie> getAllMovies() throws Exception {
        try{
            return movieRepository.getAllMoviesFromDataBase();
        }catch (Exception e){
            throw e;
        }
    }


    public void deleteDirectorByName(String directorName){
        try {
            movieRepository.deleteDirectorByName(directorName);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteAllMoviesMappedWithDirector(){
        try{
            movieRepository.deleteAllMoviesMappedWithDirector();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
