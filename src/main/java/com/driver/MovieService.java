package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){

        List<String> listOfMoviesByDirector = movieRepository.getListOfMoviesNameByDirectorName(directorName);
        listOfMoviesByDirector.add(movieName);
        movieRepository.addMovieDirectorPair(directorName, listOfMoviesByDirector);
    }


    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String name) {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieRepository.getMoviesByDirectorName(director);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String directorname) {
        movieRepository.deleteDirectorByName(directorname);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}