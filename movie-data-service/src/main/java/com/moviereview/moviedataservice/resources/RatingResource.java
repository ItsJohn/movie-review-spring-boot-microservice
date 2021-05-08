package com.moviereview.moviedataservice.resources;

import com.moviereview.moviedataservice.models.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    @GetMapping("/movieID")
    public Rating getRating(@PathVariable("movieID") String movieID) {
        return new Rating(movieID, 5);
    }
}
