package com.moviereview.moviedataservice.resources;

import com.moviereview.moviedataservice.models.Rating;
import com.moviereview.moviedataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    @GetMapping("/{movieID}")
    public Rating getRating(@PathVariable("movieID") String movieID) {
        return new Rating(movieID, 5);
    }

    @GetMapping("/users/{userID}")
    public UserRating getUserRatings(@PathVariable("userID") String userID) {
        List<Rating> ratings = Arrays.asList(
                new Rating("3", 1),
                new Rating("4", 4)
        );
        return new UserRating(ratings);
    }
}
