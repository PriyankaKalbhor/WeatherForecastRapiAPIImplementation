package rapid.com.example.RapidAPIDemo.service;

import org.springframework.http.ResponseEntity;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;

public interface RapidApiService {
    String  RapidApiGetForecastSummaryByLocationName(String cityName,Boolean isHourly) throws RapidApiException;

}
