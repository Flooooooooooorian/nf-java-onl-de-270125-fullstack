package de.neuefische.backend.weather;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class WeatherApiService {

    private static final String WEATHER_CACHE_KEY = "weatherData";

    @Cacheable(WEATHER_CACHE_KEY)
    public String getWeather(String city) {
        // Simulate a weather API call
        System.out.println("Fetching weather data for " + city+ " from API...");
        return city + ": Sunny, 26°C, " + Instant.now();
    }

    @CacheEvict(value = WEATHER_CACHE_KEY, allEntries = true)
    @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight
    public void clearWeatherCache() {
        System.out.println("Clearing weather cache...");
    }

    @CachePut(value = WEATHER_CACHE_KEY)
    public String updateWeatherCache(String city) {
        System.out.println("Updating weather cache...");
        return city + ": Sunny, 30°C, " + Instant.now();
    }
}
