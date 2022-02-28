# WebScraping-backend
### Java Springboot Application to implement Web Scraping
Please find the entire source code in the git repository.
Please find the deployable **jar file inside that target folder**
#### To run the application, type the below command, in the command prompt after navigating to location having jar file
                          java -jar test-0.0.1-SNAPSHOT.jar
                          
I have implemented WebScraping without Multithreading. Data is loaded into in memory H2 database.
#### To view the data in H2 Database, clone the entire project to your system
Open the project in any IDE, run the application as Java Application
 Go to http://localhost:8080/h2-console, copy the JDBC url specified in console.For eg:'jdbc:h2:mem:ebd2dd02-c42a-4a88-92bb-6a6fbb78b1c3'
Click connect.
Run the query "SELECT * FROM RESPONSE_MODEL"
Displays the entire web Scraped data.
