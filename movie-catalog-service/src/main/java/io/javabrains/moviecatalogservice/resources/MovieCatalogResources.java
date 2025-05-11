package io.javabrains.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MovieCatalogResources {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        //get all rated movie IDs
        //restTemplate
        //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/2", Movie.class);
        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
        .collect(Collectors.toList());


    }

}
            /*
                    Movie movie =webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

             */
