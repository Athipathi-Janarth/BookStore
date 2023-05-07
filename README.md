# BookStore


## REST API Project

### Prerequisites
1. Java Development Kit (JDK) 
2. Apache Maven
3. MySQL server

### Steps
1. Clone the project repository
2. Set up a MySQL database for the project. Create a new database 
3. Configure the database connection by updating the `application.properties` file located in the `src/main/resources` directory. Set the appropriate values for the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties. Also on the hibernate config file
4. Build Run the project using Maven. In the terminal or command prompt, execute the following command:
   ```
   mvn spring-boot:run
   ```
   This will start the REST API server at `http://localhost:8080`.


## Web Project

### Prerequisites
1. Node.js 

### Steps
1. Clone the project repository
2. Open the project's root directory.
3. Install project dependencies by running the following command:
   ```
   npm install
   ```
4. After the dependencies are installed, start the development server with the following command:
   ```
   npm start
   ```
   The ScanBook web application will be accessible at `http://localhost:3000`.
