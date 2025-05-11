package io.javabrains.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),//TIMEOUT
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),//Number of request to lookback on
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),//Percentage of error to close the circuit
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),//Closing circuit of how many seconds
            },
            //BULKHEAD PATTERN
            threadPoolKey = "movieInfoPool",// Creates a Seperate space in the server for this api request only
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "20"),// Thread Pool Size i.e How many concurrent thread are allowable for this bulkhead
                @HystrixProperty(name = "maxQueueSize", value = "10")// How many request you want queued so that they are waiting eventhough they are not consuming threads
            }
    )
    public CatalogItem getCatalogItem(Rating rating) {
        //For each movie ID, call movie  info service and get details
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
        //Put them all together
        return new CatalogItem("Movie name not found", "Desc", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found", "Desc", rating.getRating());
    }
}
