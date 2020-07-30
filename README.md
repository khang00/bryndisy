# Bryndisy
The back-end API for Bryndisy an automatic scheduler application for 1 person.
The [Front-end](https://github.com/VTKien0310/Bryndisy) is an android app.
## API
* To get all user: __GET "/"__

* To get a user by ID: __GET "/?id=[USER_ID]"__

* To get a user with their optimized tasks: __GET "optimizedTasks/?id=[USER_ID]"__

* To create a user: __POST "/"__
```
{
    "name": "khang" //user name
    "workDurationPerDay": "PT8H" //work hours, default is 8 hours
}
```

* To create a task for a user: __POST "/userTask"__
```
{
    "first" :"5f081393b15644322c2d4e47", //user id
    "second": {
        "name": "say hi",
        "duration": "PT3H",
        "deadline": "2020-07-12T07:06:59.000",
        "priority": "5",
    }   //task content
}
```

* To update a task for a user: __PUT "/userTask/{taskId}"__
```
{
    "first" :"5f081393b15644322c2d4e47", //user id
    "second": {
            "name": "say flexible REST API",
            "completed": true
    }   //task content
}
```

* To authenticate user: __POST "/authenticate"__
```
{
    "name": "khang01",
    "password": 1
}
```
## Optimize Algorithm
Sort simply with a criterion that is the task with the closest deadline, the highest priority and the smallest duration will be done first. Then compute the start date of a task by adding the start date of this task with the duration of the previous task.

![Formula](https://lh6.googleusercontent.com/B12rNdkUbkt9rCg1hcG1to1V30VWbJZgSZOhHNG00Ktrbl8FLtQ5YDOFuwsjSurFLtKbCzKhE00ou8xOfCazmRsxk6FsuOq6Pj0dmWq_)

## Technologies
Kotlin is a good tool to quickly develop an application with low bugs and errors. Kotlin has Null Safety feature which eliminates the danger of null reference exceptions, the most common pitfalls in many programming languages. Furthermore, Kotlin has data classes which are perfect DTO and also support some basic Functional Programming concepts which are good for transformation of data.

Spring Boot is perfect for API development. Spring Boot provides Inversion of Control Container and Dependency Injection. The IoC Container manages the object life cycle from creation to deletion which eliminates the need to manually manage the objects creation and deletion. The Dependency Injection pattern automatically injects the created objects into its dependent. These 2 core functionalities make the design of application architecture simple. Furthermore, Spring Boot also provides functionalities such as ORM, Http Communication, MongoDB Persistence, etc.

## Architecture
![Architecture Diagram](https://lh6.googleusercontent.com/UV_3mdIEPODVTffgxJtMP_SD8SqluRrcUAxdUaZH0J3ZE7D4GmI07_HvoZsfmUD2EuJqJySlXuSNGeez5TcTFvveyY_RndDYwukNDRCV)

Controller module is responsible for http communication between client and the API core services. Classes inside the module will parse JSON data to the DTO and pass it to the services for further processing. After processing, services will give back some DTO and the controllers then parse the DTO to JSON wrapped inside a HTTP response and transfer to the client.

All classes inside the adapter module are interfaces. By doing this, we decouple the service layer from the presentation layer ensuring that changing of services is at ease. The Task Optimizer is where we implement the task optimize algorithm. The Authentication Service is for authentication. The User service is for user features.
Repository module is simply a module containing interfaces that extends the MongoDB Persistence Repository so that we can have support for communication and parsing between MongoDB and service classes.

The Data Transfer Object module contains data structures used to transfer data between layers. Because the REST request will only contain information which is adequate for the processing of that request, parsing between a REST request body to a DTO whose fields are non-nullable requires an intermediate DTO whose fields are nullable. The RESTTask class is this intermediate DTO and the Domain Task class is for domain logic of the application. Furthermore, both classes must have the same fields so that they can be merged to each other. To address this constraint, the Task interface is created to identify the fields which are required for merging to work properly.

The configuration module contains configuration details of the spring framework. In this case, the configuration of deserializing and serializing the object to JSON so that it fits the requirements from the front-end.
