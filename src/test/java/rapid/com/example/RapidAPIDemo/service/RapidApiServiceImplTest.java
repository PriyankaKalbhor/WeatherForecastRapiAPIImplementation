package rapid.com.example.RapidAPIDemo.service;

import com.amazonaws.services.pinpoint.model.TooManyRequestsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;
import rapid.com.example.RapidAPIDemo.repository.RapidApiRepo;
import rapid.com.example.RapidAPIDemo.service.impl.RapidApiServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RapidApiServiceImplTest {

    @Mock
    private RapidApiRepo rapidApiRepo;

    @InjectMocks
    private RapidApiServiceImpl rapidApiService;

    private String locationName;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Before
    public void setData() {
        locationName = "Berlin";
    }

    @Test
    public void testRapidApiGetForecastSummaryByLocationName_Success() throws RapidApiException {
        String mockedApiResponse = "Mocked JSON Response";
        ResponseEntity<String> mockedResponseEntity = ResponseEntity.ok(mockedApiResponse);
        given(rapidApiRepo.getWeatherSummary(locationName, false)).willReturn(mockedResponseEntity);

        String result = rapidApiService.RapidApiGetForecastSummaryByLocationName(locationName, false);

        // Check response
        assertEquals(mockedApiResponse, result);
    }

    @Test
    public void testRapidApiGetForecastSummaryByLocationName_TooManyRequestsException() throws RapidApiException {

        given(rapidApiRepo.getWeatherSummary(locationName, false)).willThrow(new TooManyRequestsException("Too many requests"));

        RapidApiException exception = assertThrows(RapidApiException.class, () ->
                rapidApiService.RapidApiGetForecastSummaryByLocationName(locationName, false));

        assertEquals("Two many requests", exception.getMessage());
    }

}
