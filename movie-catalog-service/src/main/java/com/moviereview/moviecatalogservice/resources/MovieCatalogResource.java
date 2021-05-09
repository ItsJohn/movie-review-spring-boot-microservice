package com.moviereview.moviecatalogservice.resources;

import com.moviereview.moviecatalogservice.models.CatalogItem;
import com.moviereview.moviecatalogservice.models.Movie;
import com.moviereview.moviecatalogservice.models.Rating;
import com.moviereview.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    private <T>T callMircoService(String uri, Class<T> classType) {
        return this.webClientBuilder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(classType)
                .block();
    }

    @GetMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID) {
        UserRating userRating = callMircoService("http://movie-data-service/ratingsdata/users/" + userID, UserRating.class);

        return userRating.getRatings().stream().map(rating -> {
            Movie movie = callMircoService("http://movie-info-service/movies/" + rating.getMovieID(), Movie.class);

            return new CatalogItem(movie.getName(), "test", rating.getRating());
        }).collect(Collectors.toList());
    }
}
