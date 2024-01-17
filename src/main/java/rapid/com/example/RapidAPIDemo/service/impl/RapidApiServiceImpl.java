package rapid.com.example.RapidAPIDemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;
import rapid.com.example.RapidAPIDemo.repository.RapidApiRepo;
import rapid.com.example.RapidAPIDemo.service.RapidApiService;

@Slf4j
@Service
public class RapidApiServiceImpl implements RapidApiService {


    private final RapidApiRepo rapidApiRepo;

    @Autowired
    public RapidApiServiceImpl(RapidApiRepo rapidApiRepo) {
        this.rapidApiRepo = rapidApiRepo;
    }

    @Override
    public ResponseEntity<String> RapidApiGetForecastSummaryByLocationName(String locationName, Boolean isHourly) throws RapidApiException {

        ResponseEntity<String> response = null;
        try {
            log.info("Getting info from rapid api");
            response = rapidApiRepo.getWeatherSummary(locationName, isHourly);
        } catch (Exception e) {
            log.error("Error while converting to json response: " + e.getMessage());
            e.printStackTrace();
            throw new RapidApiException("An unexpected error occurred while fetching weather data.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }


}
