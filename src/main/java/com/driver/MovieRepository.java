package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MovieRepository {

        HashMap<String, Movie> movieDataBase = new HashMap<>();
        HashMap<String, Director> directorDataBase = new HashMap<>();
        HashMap<String, List<String>>  PairDataBase = new HashMap<>();

        // since we want to have a ioc controller i need to make default cunstructor
        //

        public MovieRepository(){}

        public Movie getMovieByName(String name) {

                if(movieDataBase.containsKey(name)){
                        return movieDataBase.get(name);
                }
                return null;
        }

        public Director getDirectorByName(String name) {
                if(directorDataBase.containsKey(name)){
                        return directorDataBase.get(name);
                }
                return null;
        }

        public void addMovie(Movie movie){
                Movie movieToBeAdded = new Movie(movie.name, movie.durationInMinutes, movie.imdbRating);
                movieDataBase.put(movieToBeAdded.getName(), movieToBeAdded);
        }

        public void addDirector(Director director){
                Director directorToBeAdded = new Director(director.name, director.numberOfMovies, director.imdbRating);
                directorDataBase.put(directorToBeAdded.getName(), directorToBeAdded);
        }

        public List<String> getListOfMoviesNameByDirectorName(String director){

                List<String> listOfMoviesNameByDirector = new ArrayList<>();

                if(PairDataBase.containsKey(director))
                        listOfMoviesNameByDirector = PairDataBase.get(director);

                return listOfMoviesNameByDirector;
        }

        public void addMovieDirectorPair(String director, List<String> movies) {
                PairDataBase.put(director, movies);
        }

        public List<String> getMoviesByDirectorName(String directorName) {

                if(PairDataBase.containsKey(directorName)){
                        return PairDataBase.get(directorName);
                }
                return new ArrayList<>();
        }

        public List<String> findAllMovies() {
                List<String> movies = new ArrayList<>();

                for(String movie : movieDataBase.keySet()){
                        movies.add(movie);
                }
                return movies;
        }

        public void deleteDirectorByName(String directorname) {

                if(PairDataBase.containsKey(directorname)){
                        List<String> allMoviesByDirector = PairDataBase.get(directorname);
                        for(int i = 0; i < allMoviesByDirector.size(); i++){
                                if(movieDataBase.containsKey(allMoviesByDirector.get(i))){
                                        movieDataBase.remove(allMoviesByDirector.get(i));
                                }
                        }
                        PairDataBase.remove(directorname);
                }
                if(directorDataBase.containsKey(directorname)){
                        directorDataBase.remove(directorname);
                }

        }

        public void deleteAllDirectors() {
                for(String directorName : PairDataBase.keySet()){
                        deleteDirectorByName(directorName);
                }

                for(String director : directorDataBase.keySet()){
                        directorDataBase.remove(director);
                }
        }
}