# Development Mode Setup

## MySQL requirement & DB Setup Script
To run the application locally in development mode, you will need Java 17 JDK and MySQL database setup first:
* The database setup sql script is located at: `src/main/java/org/apathinternational/faithpathrestful/db/migration/V1_0__create_apath_schema.sql`

## Local configuration file
* Create a `config/application.properties` under the project directory
* Add configuration `spring.datasource.url = jdbc:mysql://{hostname}:{port}/{dbname}?useSSL=false`
* Add configuration `spring.datasource.username = {dbusername}`
* Add configuration `spring.datasource.password = {dbpassword}`
* Add configuration `jwt.secret = {secretkey - at least 512 bits}`

## Server Startup
Under the project directory, run `./mvnw spring-boot:run`
