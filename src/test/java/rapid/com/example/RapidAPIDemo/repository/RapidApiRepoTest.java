package rapid.com.example.RapidAPIDemo.repository;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import rapid.com.example.RapidAPIDemo.exception.RapidApiException;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RapidApiRepoTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RapidApiRepo rapidApiRepo;
    private String locationName ;

    private String apiUrl;
    private String apiKey;
    private String apiHost;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setData() {
        locationName = "Berlin";

         apiUrl = "https://forecast9.p.rapidapi.com/";
         apiKey = "be17135668msh4dfe2d0ae7d203bp12dd5cjsn55b75620ed9f";
         apiHost = "forecast9.p.rapidapi.com";
    }


    @Test
    public void testGetWeatherSummary_Success() throws RapidApiException {



        String expectedUrl = apiUrl + "rapidapi/forecast/" + locationName + "/summary/";
        String responseBody = "Mocked JSON Response";
        ResponseEntity<String> mockedResponse = ResponseEntity.ok(responseBody);

        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.set("X-RapidAPI-Key", apiKey);
        expectedHeaders.set("X-RapidAPI-Host", apiHost);
        HttpEntity<String> expectedEntity = new HttpEntity<>(expectedHeaders);

        given(restTemplate.exchange(expectedUrl, HttpMethod.GET, expectedEntity, String.class))
                .willReturn(mockedResponse);

        ResponseEntity<String> result = rapidApiRepo.getWeatherSummary(locationName, false);

        assertEquals(mockedResponse, result);
    }

    @Test
    public void testGetWeatherSummary_NotFound() {

        String expectedUrl = apiUrl + "rapidapi/forecast/" + locationName + "/summary/";

        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.set("X-RapidAPI-Key", apiKey);
        expectedHeaders.set("X-RapidAPI-Host", apiHost);
        HttpEntity<String> expectedEntity = new HttpEntity<>(expectedHeaders);

        given(restTemplate.exchange(expectedUrl, HttpMethod.GET, expectedEntity, String.class))
                .willThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND, "Not Found") {
                });

        RapidApiException exception = assertThrows(RapidApiException.class,
                () -> rapidApiRepo.getWeatherSummary(locationName, false));

        assertEquals("Resource not found", exception.getMessage());
    }

}

