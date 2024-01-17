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

# APIs
## API 1: Get the Weather forecast summary of Any city using API (RapidApiGetForecastSummaryByLocationName)

1. Url : http://localhost:8080/api/forecast-summary/:locationName
2. Output :
In the below picture getting output for Forecast Summary By Location Name
![Project Logo](/home/lnv66/Downloads/forecastSummaryByLocationName.png)




## API 2: Get hourly Weather forecast details of Any city using API (RapidApiGetHourlyForecastByLocationName)

1. Url : http://localhost:8080/api/hourly/forecast-summary/:locationName
2. Output :
   In the below picture getting output for Forecast Summary By Location Name
![Project Logo](/home/lnv66/Downloads/forecastSummarybyhourly.png)







