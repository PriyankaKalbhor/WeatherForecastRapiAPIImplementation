# WeatherForecastRapiAPIImplementation

Write a server by using SpringBoot Java and integrate Weather API from Rapid API. On the basis of that integration, expose your RESTful APIs as follows with JSON response. Authentication Method: Header-based authentication with random client id and random client secret

# How to Run Project
1. Download the zip folder Or clone the repository
2. Open project in Tools( Intellij)
3. Build the project
4. Run the Project Run as Spring boot app


# Swagger API Documentation
1. Run the application
2. Open the swagger documentation using below link

http://localhost:8080/api/swagger-ui/index.html

# Junit Mockito Test Cases

Written the Junit Mockito test cases for each component.Please check the test package.
# Files name:
1. controller -> RapidApiControllerTest
2. service -> RapidApiServiceImplTest
3. repository -> RapidApiRepoTest


# APIs
## API 1: Get the Weather forecast summary of Any city using API (RapidApiGetForecastSummaryByLocationName)

#### Response

# output of 2 API
## API 1: Get the Weather forecast summary of Any city using API (RapidApiGetForecastSummaryByLocationName)
In the below picture getting output for Forecast Summary By Location Name
![image](/home/lnv66/Desktop/RapidAPIGit/WeatherForecastRapiAPIImplementation/images/forecastSummaryByLocationName.png)


## API 2: Get hourly Weather forecast details of Any city using API (RapidApiGetHourlyForecastByLocationName)
In the below picture getting output for houry Forecast By Location Name
![image](/home/lnv66/Desktop/RapidAPIGit/WeatherForecastRapiAPIImplementation/images/forecastSummarybyhourly.png)


#### Request

```bash
curl --location --request GET 'http://localhost:8080/api/forecast-summary/Berlin' \
--header 'Cookie: JSESSIONID=8C147654F13859A16DBBBAD863F28627'


## API 2: Get hourly Weather forecast details of Any city using API (RapidApiGetHourlyForecastByLocationName)

curl --location --request GET 'localhost:8080/api/hourly/forecast-summary/Berlin' \
--header 'Cookie: JSESSIONID=8C147654F13859A16DBBBAD863F28627'




