# lpod-tech-challenge
LPOD Technical challenge

Framework:

The automation framework is using Java, Cucumber, Springframework, Selenium, Hamcrest assertion framework, Annotations and Slf4j-log4j. 

Selenium Page Object Model (POM) is used in the framework. One of the main advantages of POM is that whenever the UI changes for any single web page, no code changes are required in the test case itself; only the page object would need to change to cater for the modifications in the web page. Typically, this amendment would only be on a locator to the web element(s) and if necessary any method that would be affected by this change. By implementing this kind of design, the code becomes more maintainable, reusable and less prone to duplication.

It is built using Java programming language so it has the advantage of using Inheritance, Abstraction, Encapsulation and reusability of common code. The package is such that it separates pages(having implementation of web elements), step implementations, feature files and resources. The package for the page class is based on the application structure. 

The Page class has the main implementation of basic selenium methods which are available for the inherited classes. 

The Pages and Step implementations having logging which is very helpful for analysis and debugging purpose. 

The framework has properties setup in file to have the flexibility of configuring the variables. 


Running the scenario:

To resolve dependency issues please execute the command 'mvn clean install -DskipTests'

To run the scenario execute the command 'mvn clean test' or 'mvn clean verify' or right click on the feature file and select 'Run' option.


