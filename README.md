# 3s Consultancy - Job Seeker API

This README provides instructions on how to set up and run the 3s Consultancy Job Seeker API project using Docker and Postman.

**Prerequisites:**

1. **Install Docker and Docker Compose:**
   - **Install Docker:**
      - Download and install Docker Desktop from the official Docker website (https://www.docker.com/).
      - Follow the installation instructions for your operating system.
   - **Install Docker Compose:**
      - Install Docker Compose using your package manager:
         - **On macOS/Linux:**
            ```bash
            sudo apt-get install docker-compose 
            ```
         - **On Windows:** 
            - Download and install Docker Compose from the official Docker Compose website. 
            - Add the Docker Compose executable to your system's PATH.

**Running the Project:**

1. **Clone the Repository:**
   - Clone this repository to your local machine using Git:
      ```bash
      git clone https://github.com/premnathmali007/3sconsultancy.git 3s-consultancy
      ```

2. **Navigate to the Project Directory:**
   - Change to the project directory:
      ```bash
      cd 3s-consultancy 
      ```

3. **Start the Application:**
   - Run the following commands to start the application using Docker Compose:
      ```bash
      docker-compose down 
      docker-compose up  
      ```
      This will build and start the application and the database containers in detached mode.

**Testing with Postman:**

1. **Import Postman Collection:**
   - Import the `JobSeeker_API_Tests.postman_collection.json` file into Postman.

2. **Set Base URL:**
   - In Postman, set the base URL for the collection to `http://localhost:8090`.

3. **Load Sample Data:**
   - Execute the request named "Load Test Data" in the collection. This will populate the database with sample job seeker data.

4. **Verify Data:**
   - Execute the request named "Get All Job Seekers" in the collection. 
   - Verify that the response contains the expected list of job seekers.

**Troubleshooting:**

- If you encounter any issues, check the Docker Compose logs for errors:
   ```bash
   docker-compose logs app
   docker-compose logs db