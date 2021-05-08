package com.moviereview.movieinfoservice.resources;

import com.moviereview.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @GetMapping("/{movieID}")
    public Movie getMovieInfo(@PathVariable("movieID") String movieID) {
        return new Movie(movieID, "Test name");
    }
}
