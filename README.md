# Fuel Consumption Management Application
Spring, JPA, Hibernate, HSQLDB Database
"# Fuel-Consumption-Management-Application"
## Project Setup
 - Requirement
    - jdk 1.8 or higher
    - maven
    - apache tomcat or other server where java application can be deployed
    
## Documentation
 After setup of the project documentation can be found in the location: base-url/swagger-ui.html
    
## Project Description
A fuel consumption management application for small transportation company. 
This application initially contains only RESTful API with JSON payloads in response, but in future the functionality can be increased. In memory database(HSQLDB) is used to store data initially but it can be replaced by other data storage(Msql, OracleDB, etc.)  
<br/>
The Application contains The following functionality - 

## Fuel Consumption Registration
At the time of registration the following data should be given - 
 - 	fuel type(Ex. 95, 98 or D)
 - price per litter in EUR (Ex. 10.10
 - volume in litters (Ex. 12.5)
 - date (Ex. 01.21.2018)
 - driver ID (Ex. 12345)

It is possible to register one single record or bulk consumption. The request format is given below. You need to post a json array of json object. If you post single data in array then it will register that record if bulk data is given then those data would be inserted in the persistent storage. All the json object of the json array need to be valid else application would throw error message. 

#### request url  :   
```
  base-url/create-fuel-registration
```
#### request type :
```
  post
```
#### post data    :
```
            [{
                "fuelType" : "M",
                "pricePerLiter" : 20.2,
                "volume" : 2.2,
                "date" : "10.06.2018",
                "driverId" : "123435"
            },
            {
                "fuelType" : "E",
               "date" : "7.3.2018",
                "pricePerLiter" : 24.2,
                "volume" : 2.2,
                "driverId" : "123435"                
            }
            ]
```
#### response data :
```
 
            {
                "message": "Data inserted successfully."
            }

```
#### Curl Command 
``` 

curl -X POST "base-url/create-fuel-registration" -H "accept: */*" -H "Content-Type: application/json" -d "[ { \"fuelType\": \"D\", \"pricePerLiter\": 10, \"volume\": 15.2, \"driverId\": \"12345\", \"date\": \"01.01.2020\" }]"


curl -X POST "base-url/create-fuel-registration" -H "accept: */*" -H "Content-Type: application/json" -d "[ { \"fuelType\": \"D\", \"pricePerLiter\": 10, \"volume\": 15.2, \"driverId\": \"12345\", \"date\": \"01.01.2020\" }, { \"fuelType\": \"20\", \"pricePerLiter\": 9.15, \"volume\": 31, \"driverId\": \"12245\", \"date\": \"01.05.2019\" }]"


```
## Fuel Consumption data retrieval
### total spent amount of money grouped by month
It is possible to get all the data of all year group by month or a specific year can be given as post parameter to get all the related month's data.   
A empty json object need to be post to the following url to get all the data:
#### request url 
```
base-url/get-total-spent-amount
```
#### request type
``` 
post
```
#### request data
``` 
{}
```
or like following,
``` 
{
    "year" : 2017
}
```
#### response data
``` 
[
  {
    "year": 2017,
    "costData": [
      {
        "month": "January",
        "totalCost": 126.25
      },
      {
        "month": "June",
        "totalCost": 126.25
      }
    ]
  }
```

#### curl command
``` 
curl -X POST "base-url/get-total-spent-amount" -H "accept: */*" -H "Content-Type: application/json" -d "{}"

curl -X POST "base-url/get-total-spent-amount" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"year\" : 2017 }"
```

### list fuel consumption records for specified month (each row should contain: fuel type, volume, date, price, total price, driver ID) 
It is possible to get all the data of all year or a specific year can be given as post parameter to get all the related month's data. It is also possible to get data for specific month of specific year. Then the post json object should contain both year and month param.    
A empty json object need to be post to the following url to get all the data:
#### request url 
```
base-url/get-fuel-consumption-record
```
#### request type
``` 
post
```
#### request data
``` 
{}
```
or like following,
``` 
{
    "year" : 2017
}
```
or like following,
``` 
{
    "year" : 2017,
    "month" : 1
}
```
#### response data
``` 
[
  {
    "year": 2017,
    "records": [
      {
        "month": "January",
        "data": [
          {
            "id": 4,
            "fuelType": "D",
            "pricePerLiter": 10.1,
            "volume": 12.5,
            "driverId": "12345",
            "date": "4.1.2017"
          }
        ]
      },
      {
        "month": "June",
        "data": [
          {
            "id": 5,
            "fuelType": "D",
            "pricePerLiter": 10.1,
            "volume": 12.5,
            "driverId": "12345",
            "date": "4.6.2017"
          }
        ]
      }
    ]
  }
]
```

#### curl command
``` 
curl -X POST "base-url/get-fuel-consumption-record" -H "accept: */*" -H "Content-Type: application/json" -d "{}"

curl -X POST "base-url/get-fuel-consumption-record" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"year\" : 2017}"

curl -X POST "base-url/get-fuel-consumption-record" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"year\" : 2017, \"month\": 1}"
```



### Statistics for each month, list fuel consumption records grouped by fuel type (each row should contain: fuel type, volume, average price, total price), Every request can be made either for all drivers or for the specific driver (identified by id).
It is possible to get all the data of all year or a specific year can be given as post parameter to get all the related month's data. It is also possible to get data for specific month of specific year. Then the post json object should contain both year and month param. If we want data for specific driver then additional param 'driverId' should be added in the post request.
A empty json object need to be post to the following url to get all the data:
#### request url 
```
base-url/get-fuel-consumption-statistics
```
#### request type
``` 
post
```
#### request data
``` 
{}
```
or like following,
``` 
{
    "year" : 2017
}
```
or like following,
``` 
{
    "year" : 2017,
    "month" : 1
}
```
or like following,
``` 
{
    "year" : 2017,
    "month" : 1,
    "driverId" : "11111",
}
```
#### response data
``` 
[
  {
    "year": 2016,
    "records": [
      {
        "month": "January",
        "data": [
          {
            "volume": 15.2,
            "fuelType": "#",
            "totalPrice": 152,
            "averagePrice": 10
          },
          {
            "volume": 12.5,
            "fuelType": "D",
            "totalPrice": 126.25,
            "averagePrice": 10.1
          }
        ]
      }
    ]
  },
  {
    "year": 2019,
    "records": [
      {
        "month": "January",
        "data": [
          {
            "volume": 12.5,
            "fuelType": "D",
            "totalPrice": 126.25,
            "averagePrice": 10.1
          }
        ]
      }
    ]
  }
]
```

#### Curl command
``` 
curl -X POST "base-url/get-fuel-consumption-statistics" -H "accept: */*" -H "Content-Type: application/json" -d "{}"

curl -X POST "base-url/get-fuel-consumption-statistics" -H "accept: */*" -H "Content-Type: application/json" -d "{\"driverId\" : \"11111\"}"

```
## Unit Testing
The unit test can be found on the following directory
```
src\test\java\com\forhadmethun\fuelconsumptionmanagement\registration\ 
```