# Agilethought practices

# Accounting Spring Boot
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
3. CRUD repositories implementations could be found in "DAO" folder
4. Initial data.sql script to initial querys and insert rows on PERSON table
## UNIT Testing (JUNIT 5)
1. Unit testing has been implemented with 91.2% of coverage
2. MockMvc used for unit testing
## Log4J
1. Logger setting placed on log4j2.xml for file and console logging

# PML Spring Batch Approach
Solution in Spring Boot + Spring Batch
## REST API
1. Could be found in controllers folder
There 2 enpoint to query results:
   * /api/transactions/risk-results-ip for query risk operations grouped by ip
   * /api/transactions/mx for query risk operations gruoped by transactions
## JOBS
Could be found in BatchConfiguration class, there are 2 JOB definition
* initialSeedJob JOB Created for bulk 2 csv files to tables (accounts.csv, transactions.csv)
   * accounts-fixed.csv (500,000 rows)
   * transactions.csv (600,000 rows)
* analyzeTransactionsJob JOB Created for analyze for transactions to Money Laundering Suspicious Detection
## Readers
2 types of readers has been used, JPAPagedReader and FlatFile (Csv) Reader, you can see at BatchConfiguration class 
## Multi-threading
In order to get the best perfoance an Task executor of 20 threads has been configured for analyze trasactions (Steps with 1000 rows readers limit)
## JOBS Listener
Created in class JobListenerPML in order to inform a resume by WhatsApp.
# Pentaho ETL
Simple ETL to consume /api/transactions/mx in order to generate a CSV with all data recivied. Variables could be setted in order to configure URL y file PATH