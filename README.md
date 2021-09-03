# RestAssured-VS-Retrofit
##About
It's a tiny API testing project aiming for a comparison of the (RestAssured)[https://rest-assured.io/] and (Retrofit2)[https://square.github.io/retrofit/] libraries. The project has two test classes: *RestAssuredTests* and *RetrofitTests* with the corresponding content. Each class has two tests of equal functionality, one test creates a new 'Pet' resource via the POST request with the predefined ID and another test verifies that the resource with this ID exists and has the expected data via the GET request. The order of tests execution is important here, the test with the GET should follow after the test with POST otherwise it will fail.

##Tech Stack
* Java 11
* Gradle 7.1
* JUnit 5
* Rest Assured 4.4.0
* Retrofit 2.9.0

##How to run
You can run the tests directly in your Java IDE if it has the JUnit plugin (usually it does) or via the following commands from the command line in the project's root folder:
To run all the tests:
```
./gradlew clean test
```
or to run a specific class of tests:
```
./gradlew clean test --tests={name of desired test class} 
e.g. ./gradlew clean test --tests=RestAssuredTests
```

_______________________________________________________________________________________


# selenide-cucumber-demo
##About
It's a small UI testing project that stands to show perks of (Selenide)[https://selenide.org/] library usage instead of pure Selenium. The project is based on a usual Cucumber structure and Cucumber reporting. 

##Tech Stack
* Java 11
* Gradle 7.1
* JUnit 5
* Selenide 5.24.0
* Cucumber 6.11.0

##How to run
You can run the tests directly in your Java IDE (if it has the JUnit plugin) in the *feature* files from the */src/test/resources/features* folder. Or from the command line in the project's root folder:
To run all the tests:
```
./gradlew clean test
```
or you can run a predefined build task with the tests:
```
./gradlew cucumber
```


## Test Repport
The report generated by Cucumber is available in the *\reports* folder and it also posted on the (Cucumber Report Portal)[ https://reports.cucumber.io/].
