# agilethought practices

# Accounting
MS for bank account managing

## REST API
1. Could be found in controllers folder  
## Usage of Lambdas and Streams
1. Class AccountServiceImpl method findAccount use diferent approaches of streams and lamdas with diferent collectors and functional interfaces  
## Usage of Regex
1. You can find the class AddProductRequestVO in a @Pattern validation, for validate an account number
2. In AccountServiceImpl class you could find one more approach to "hide" the customer name under PSI implementations (OWASP recommendations)
## Usage of Validations
1. You can find the implementation on each VO class inside request package (example AddProductRequestVO class)
2. Also a direct argument validation could be found at AccountsController
## Exception handlers
1. A Rest Controller Advice has been implemented in GlobalExceptionHandler class
2. Messages are setting up on message.properties
## JPA and repositories
1. Implementation of entities by JPA and Apache Derby
2. JPQL implementation could be found in AccountingApiApplicationTests clas throught EntityManager
3. CRUD repositories implementations could be found in "dao" folder
4. Initial data.sql script to initial querys and insert rows on PERSON table
## UNIT Testing (JUNIT 5)
1. Unit testing has been implemented with 91.2% of coverage
2. MockMvc used for unit testing
## Log4J
1. Logger setting placed on log4j2.xml for file and console logging
