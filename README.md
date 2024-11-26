```
 ██████ ██   ██ ██████   ██████  ███    ███  █████  ███    ███  ██████  ███    ██ 
██      ██   ██ ██   ██ ██    ██ ████  ████ ██   ██ ████  ████ ██    ██ ████   ██ 
██      ███████ ██████  ██    ██ ██ ████ ██ ███████ ██ ████ ██ ██    ██ ██ ██  ██ 
██      ██   ██ ██   ██ ██    ██ ██  ██  ██ ██   ██ ██  ██  ██ ██    ██ ██  ██ ██ 
 ██████ ██   ██ ██   ██  ██████  ██      ██ ██   ██ ██      ██  ██████  ██   ████ 
```
---
# ChromaMon - Rogers Analysis Service
This is the ChromaMon Rogers Analysis service, responsible for carrying out chromatographic analysis on transformers using the Rogers method. It consumes data from the data-ingestion queue in RabbitMQ, processes the data using Spring Batch and stores the results in the PostgreSQL database, as well as updating additional information in MongoDB.

## Features
- Consumes transformer data from the data-ingestion queue in RabbitMQ.
- Performs chromatographic analyses using the Rogers method.
- Saves analysis results in the PostgreSQL database.
- Updates the analysis documents in MongoDB with the results.
- It has an automatic schedule for processing every 4 hours (can easily change).
- Allows processing to be triggered manually via a REST API.

## Technologies used:
- Java 17
- Spring Boot (Spring Batch, Spring Data JPA)
- RabbitMQ
- PostgreSQL
- MongoDB
- Docker

## Requirements
Make sure the following tools are installed:

- Java 17
- Maven
- Docker and Docker Compose
- RabbitMQ
- PostgreSQL
- MongoDB

## Setting up the environment
1. Configuring RabbitMQ
   - Make sure RabbitMQ is running on the default port ```5672```.
   - Create a queue called ```data-ingestion```.
2. MongoDB configuration
   - Start a MongoDB instance.
   - Configure the database and collection in the application.properties file or in environment variables:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/chromamon
   ```
3. PostgreSQL configuration
   - Start a PostgreSQL instance.
   - Create two schemas:
   - **transformer_info**: for information about the transformers.
   - **analysis_results**: to store the analysis results.

## Installation and execution
1. **Local compilation and execution:**
   1. Clone the repository:
       ```bash
       git clone https://github.com/Paulo-Possatto/chroma-rogers-analysis.git
       ```
   2. Access the project directory:
       ```bash
       cd chroma-rogers-analysis
       ```
   3. Compile and package the project:
       ```bash
       mvn clean package
       ```
   4. Run the service:
       ```bash
       java -jar target/chroma-rogers-analysis-1.0.0.jar
       ```
2. **Using Docker (Recommended):**
   1. Build the Docker image:
       ```bash
       docker build -t chroma-rogers-analysis .
       ```
   2. Run the service with Docker Compose:
      ```bash
      docker-compose up -d
      ```

## Endpoints
1. **Start Analysis:**
- **URL:** POST /rogers-analysis/start
- **Description:** Starts processing manually.
- **Response:**
   - **200 OK** - Job started successfully.
   - **500 Internal Server Error** - Job failed to start.

## Project structure
```plaintext
chroma-rogers-analysis/
├── src/
│ ├── main/
│ │ ├── java/com/chromamon/analysis/rogers/
│ │ ├── config/ # Spring and Batch configurations
│ │ ├── controller/ # REST APIs
│ │ ├── model/ # Data models
│ │ ├── processor/ # Batch processors
│ │ ├── reader/ # Data Reader (RabbitMQ)
│ │ ├── repository/ # Repositories for PostgreSQL/MongoDB
│ │ ├── scheduler/ # Scheduled tasks
│ │ ├── writer/ # Data Writers
│ │ └── resources/
│ │ ├── application.properties # Application settings
│ │ ├── logback-spring.xml # Log configuration
│ ├── test/ # Unit and integration tests
├── Dockerfile # Docker image
├── docker-compose.yml # Docker Compose configuration
├── pom.xml # Maven dependencies
└── README.md # Project documentation
```

## Contributing
Contributions are welcome! Follow the steps below to collaborate:

1. Fork the project.
2. Create a branch for your feature/fix:
    ```bash
    git checkout -b feature/my-feature
    ```
3. Send a Pull Request with your changes.

## License
This project is distributed under the Apache-2.0 license. See the LICENSE file for more details.

