package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this is the class where i will write my apis
@RestController
@RequestMapping("/movies")

public class MovieController {

    @Autowired
    MovieService movieService;

//   1 . Post http://localhost:8080/movies/add-movie
/*
        {
            "name": , "hindi film",
            "durationInMinutes": 180,
            "imdbRating": 9.5
         }
*/
     @PostMapping("/add-movie")
     public ResponseEntity<String> addMovie(@RequestBody()Movie movie){
        movieService.addMovie(movie);
        return  new ResponseEntity<>("Success", HttpStatus.OK);
     }

//    2.   POST http://localhost:8080/movies/add-director

/*
        {
            "name": , "Bharat Kumar",
            "numberOfMovies": 10,
            "imdbRating": 9.5
         }
*/
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody()Director director){
        movieService.addDirector(director);
        return  new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // Put : http://localhost:8080/movies/add-movie-director-pair
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("moviename")String moviename, @RequestParam("directorname") String directorname){
        movieService.addMovieDirectorPair(moviename, directorname);
        return  new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 4. GET http://localhost:8080/movies/get-movie-by-name/{name}

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    //  5 .Get  http://localhost:8080/movies/get-director-by-name/{name}
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    // 6.  Get : http://localhost:8080/movies/get-movies-by-director-name/{director}
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> moviesByDirector = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(moviesByDirector, HttpStatus.OK);
    }

    //  7.  GET /movies/get-all-movies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> listOfAllMovies = movieService.findAllMovies();
        return new ResponseEntity<>(listOfAllMovies, HttpStatus.OK);
    }




    // 8. DELETE /movies/delete-director-by-name

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorname") String directorname){

        movieService.deleteDirectorByName(directorname);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    //  9.  DELETE /movies/delete-all-directors
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("success", HttpStatus.OK);

    }




}