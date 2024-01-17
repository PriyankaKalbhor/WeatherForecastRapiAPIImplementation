package rapid.com.example.RapidAPIDemo.service;

import org.springframework.http.ResponseEntity;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;

public interface RapidApiService {
    ResponseEntity<String>  RapidApiGetForecastSummaryByLocationName(String cityName,Boolean isHourly) throws RapidApiException;

}
