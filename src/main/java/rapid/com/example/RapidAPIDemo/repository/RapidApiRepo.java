package rapid.com.example.RapidAPIDemo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;

@Slf4j
@Repository
public class RapidApiRepo {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.host}")
    private String apiHost;

    public final RestTemplate restTemplate;

    @Autowired
    public RapidApiRepo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<String> getWeatherSummary(String locationName, Boolean isHourly) throws RapidApiException {

        String url = null;
        if(isHourly){
            log.info("getting all HOURLY summary by LOCATION NAME");
            url = apiUrl + "rapidapi/forecast/" + locationName + "/hourly/";
        }else {
            log.info("getting all summary by LOCATION NAME");
            url = apiUrl + "rapidapi/forecast/" + locationName + "/summary/";
        }

        // Set headers for the HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = null;

        try {
            log.info("Calling rapid third party api");
            response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);
        } catch (HttpStatusCodeException e) {

            // Handle status code exceptions
            HttpStatus statusCode = (HttpStatus) e.getStatusCode();

            if (statusCode == HttpStatus.NOT_FOUND) {
                throw new RapidApiException("Resource not found", statusCode.value());
            } else if (statusCode == HttpStatus.UNAUTHORIZED) {
                // Check 401 Unauthorized
                throw new RapidApiException("Unauthorized access", statusCode.value());
            } else if (statusCode == HttpStatus.TOO_MANY_REQUESTS) {
                // Check two many requests
                throw new RapidApiException("Two many requests, Please try sometime", statusCode.value());
            } else {
                // Check other status code exception
                throw new RapidApiException("An error occurred", statusCode.value());
            }
        } catch (Exception e) {
            // Handle other exceptions
            throw new RapidApiException("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }


//    public ResponseEntity<String> getWeatherSummaryHourlyBasis(String locationName) {
//
//        String url = apiUrl + "rapidapi/forecast/" + locationName + "/hourly/";
//
//        // Set headers for the HTTP request
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-RapidAPI-Key", apiKey);
//        headers.set("X-RapidAPI-Host", apiHost);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = null;
//
//        try {
//            log.info("Calling rapid third party api");
//            response = restTemplate.exchange(
//                    url, HttpMethod.GET, entity, String.class);
//        } catch (HttpStatusCodeException e) {
//
//            // Handle status code exceptions
//            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
//        }
//        log.info("response from rapid api: " + response.getBody());
//        return response;
//    }



}
