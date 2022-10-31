package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

// here i will write the list of apis
@RestController
@RequestMapping("movies")
public class MovieController {

        @Autowired
        MovieService movieService;

        //    1.Add a movie: POST /movies/add-movie // working fine
        @PostMapping("/add-movie")
        public ResponseEntity<String> addMovie(@RequestBody()Movie movieToBeAdded){
                movieService.addMovie(movieToBeAdded);
                return new ResponseEntity<String>("Success", HttpStatus.OK);
        }


        //  2.Add a director: POST /movies/add-director // working fine
        @PostMapping("/add-director")
        public ResponseEntity<String> addDirector(@RequestBody()Director directorToBeAdded){
                movieService.addDirector(directorToBeAdded);
                return new ResponseEntity<>("Success", HttpStatus.OK);
        }


        //      3.  Pair an existing movie and director: PUT /movies/add-movie-director-pair
        // working fine

        @PutMapping("/add-movie-director-pair")
        public ResponseEntity<String> addMovieDirectorPair(@RequestParam("directorname")String directorname, @RequestParam("moviename")String moviename){

                movieService.addMovieDirectorPair(directorname, moviename);

                return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }




//        6. Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}

        @GetMapping("/get-movies-by-director-name/{director}")
        public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){

                List<Movie> listOfMoviesMadeByDirector = movieService.getListOfMoviesMadeByDirector(director);

                List<String> movieNames = new ArrayList<>();

                for(int i = 0; i < listOfMoviesMadeByDirector.size(); i++){
                        movieNames.add(listOfMoviesMadeByDirector.get(i).getName());
                }
                return new ResponseEntity<>(movieNames, HttpStatus.FOUND);
        }

        // 4. Get Movie by movie name: GET /movies/get-movie-by-name/{name} // working fine

        @GetMapping("/get-movie-by-name/{name}")
        public ResponseEntity<Movie> getMovieByName(@PathVariable String name){

                Movie movie = movieService.getMovieByName(name);
                return new ResponseEntity<>(movie, HttpStatus.FOUND);
        }

        //5 .Get Director by director name: GET /movies/get-director-by-name/{name}

        // working fine
        @GetMapping("/get-director-by-name/{name}")
        public ResponseEntity<Director> getDirectorByName(@PathVariable String name){

                Director director = movieService.getDirector(name);
                return new ResponseEntity<>(director, HttpStatus.FOUND);
        }


        // 7  Get List of all movies added: GET /movies/get-all-movies

        @GetMapping("/get-all-movies") // working fine
        public ResponseEntity<List<String>> findAllMovies(){
                List<Movie> allMovies = movieService.getAllMovies();
                List<String> allMoviesNames  = new ArrayList<>();

                for(int i = 0; i < allMovies.size(); i++){
                        allMoviesNames.add(allMovies.get(i).getName());
                }
                return new ResponseEntity<>(allMoviesNames, HttpStatus.FOUND);
        }

        //  8.  Delete a director and its movies from the records: DELETE /movies/delete-director-by-name

//        @DeleteMapping("/movies/delete-director-by-name")
//        public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName")String directorName){
//
//
//                movieService.deleteDirectorByName(directorName);
//                return new ResponseEntity<>("Success", HttpStatus.OK);
//        }
//
//// 9.        Delete all directors and all movies by them from the records: DELETE /movies/delete-director-by-name
//
////        Controller Name - deleteAllDirectors
////                (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)
//
//        @DeleteMapping("/delete-director-by-name")
//        public ResponseEntity<String> deleteAllDirectors(){
//
//                movieService.deleteAllMoviesMappedWithDirector();
//                return new ResponseEntity<>("Success", HttpStatus.OK);
//
//        }
}
