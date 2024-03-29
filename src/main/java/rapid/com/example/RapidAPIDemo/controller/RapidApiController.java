package rapid.com.example.RapidAPIDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;
import rapid.com.example.RapidAPIDemo.service.RapidApiService;

@RestController
@RequestMapping("/api")
public class RapidApiController {

    private final RapidApiService rapidApiService;

    @Autowired
    public RapidApiController(RapidApiService rapidApiService) {
        this.rapidApiService = rapidApiService;
    }

    // Get weather forecast summary by location
    @GetMapping("/forecast-summary/{locationName}")
    public ResponseEntity<Object>  rapidApiGetForecastSummaryByLocationName(@PathVariable String locationName) throws RapidApiException {

        String jsonResponse = rapidApiService.RapidApiGetForecastSummaryByLocationName(locationName, false);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonResponse);
    }

    // Get hourly weather forecast summary by location
    @GetMapping("/hourly/forecast-summary/{locationName}")
    public ResponseEntity<Object> rapidApiGetHourlyForecastByLocationName(@PathVariable String locationName) throws RapidApiException {

        String jsonResponse= rapidApiService.RapidApiGetForecastSummaryByLocationName(locationName, true);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonResponse);
    }

}
