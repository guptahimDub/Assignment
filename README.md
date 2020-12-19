# Test_Assignment - By Himanshu Gupta

# REST API for Foreign Exchange Rates

### This repository consists of below files:
- `Application` -Consists of the main function and configuration of swagger API. 
- `Controller`  - Consists of end-point urls.
- `Model`       - Consists of currency, rate and date (to be stored in in-memory database).
- `Repository`  - The methods used to interact with database are present.
- `Service`     - Xml parser (or Rest API call) and other conversion logics are present.
- `Properties`  - Consisits of in-memory DB details.
- `XML file`    - Consisits of exchange rates on different time period (based on currency).

## Standalone Foreign Exchange Rate Application

## Prerequisites
- [Java]
- [Tomcat 8.5] 

## Technologies Used

- Spring Boot
- Spring MVC Test framework and JUnits
- Swagger API
- Xml Parser
- RestTemplate
- Maven

## Installation Steps
1. Clone the Git Repository.
2. Build the project using Maven: `mvn clean install` - under \ForeignExchangeRateAPI
3. The `jar` file will be created under the target folder - \target\ForeignExchangeRate-0.0.1-SNAPSHOT.jar
4. Run Application by either of the below commands:
    i.)  `mvn spring-boot:run` - under \ForeignExchangeRateAPI\               OR
    ii.) `java -jar ForeignExchangeRate-0.0.1-SNAPSHOT.jar` - under \target folder run command

## Usage
Below are the different REST APIs calls which can be made (List Of Operations):

1. GET http://localhost:8081/api/foreignExRate/getAllCurrency         - `Get all the available Currrency`                            

2. GET http://localhost:8081/api/foreignExRate/getAllRates            - `Get all the available Rates at all available dates`                            

3. GET http://localhost:8081/api/foreignExRate/all/{date}             - `Get the Foreign Exchange Rate at a Particular day`                      

4. GET http://localhost:8081/api/foreignExRate/convertRatesToEuro     - `Convert Amount in other currency to Euro`   

5. GET http://localhost:8081/api/foreignExRate/convertRates           - `Convert Amount in currency 1 to currency 2`                    

6. GET http://localhost:8081/api/foreignExRate/getAllRatesByCurrency  - `Get all Rates by Currency`

7. GET http://localhost:8081/api/foreignExRate/{date}                 - `Get all Rates By Date and Currency`

Both have embedded Tomcat and In-memory ActiveMQ. The application can be accessible using the below URL.

## The Request can be initiated using POSTMAN or SWAGGER console.
 `http://localhost:8081/swagger-ui.html#!/` - SWAGGER URL for initiating GET Request.
 
 
 Snippet of Swagger API and Postman:
 
 ![ss1](https://user-images.githubusercontent.com/56262858/102678651-b8267400-41a1-11eb-8628-fb797d272132.PNG)
 
 
![ss2](https://user-images.githubusercontent.com/56262858/102678662-c2487280-41a1-11eb-898b-70a645ee3601.PNG)


![ss3](https://user-images.githubusercontent.com/56262858/102678666-c7a5bd00-41a1-11eb-9b22-60cfd0a8f997.PNG)


![ss4](https://user-images.githubusercontent.com/56262858/102678672-cd030780-41a1-11eb-8e49-8198726c5c8c.PNG)


![ss5](https://user-images.githubusercontent.com/56262858/102678675-d12f2500-41a1-11eb-90a8-9fb209142411.PNG)


![ss6](https://user-images.githubusercontent.com/56262858/102678676-d55b4280-41a1-11eb-805c-4f21931af370.PNG)

 
