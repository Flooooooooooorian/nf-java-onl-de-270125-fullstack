package de.neuefische.backend.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherApiService weatherApiService;

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city) {
        return weatherApiService.getWeather(city);
    }

    @DeleteMapping("/clear-cache")
    public void clearWeatherCache() {
        weatherApiService.clearWeatherCache();
    }

    @PutMapping("/{city}")
    public String updateWeather(@PathVariable String city) {
        return weatherApiService.updateWeatherCache(city);
    }
}
