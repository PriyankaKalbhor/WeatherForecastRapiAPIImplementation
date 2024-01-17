package rapid.com.example.RapidAPIDemo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
import rapid.com.example.RapidAPIDemo.service.RapidApiService;

import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RapidApiControllerTest {


    @Mock
    private RapidApiService rapidApiService;

    @InjectMocks
    private RapidApiController rapidApiController;

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
    public void getForecastSummary() throws RapidApiException {

        given(rapidApiService.RapidApiGetForecastSummaryByLocationName(locationName, false)).willReturn("Mocked JSON Response");
        ResponseEntity<Object> response = rapidApiController.rapidApiGetForecastSummaryByLocationName(locationName);
        Assertions.assertNotNull(response);
    }

    @Test
    public void getHourlyForecastSummary() throws RapidApiException {

        given(rapidApiService.RapidApiGetForecastSummaryByLocationName(locationName, true)).willReturn("Mocked JSON Response");
        ResponseEntity<Object> response = rapidApiController.rapidApiGetHourlyForecastByLocationName(locationName);
        Assertions.assertNotNull(response);
    }

}
