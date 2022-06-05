## About

The project **test-texoit** is a test being carried out for the company TexoIT.

---

## Technologies used

The project was developed using the following technologies

- Java
- Maven
- Spring
- Spring Boot
- Spring Data JPA
- Banco de dados H2
- Lombok
- Swagger

---

## How to download and start the project

```bash
    # Clone the repository
    git clone https://github.com/JulioDosSantos/test-texoit/
    # Enter directory
    cd test-texoit
    # Install dependencies
    mvn clean package
    # Start the project
    java -jar target/test-texoit.jar

```

---

## How to test the project

	# Access Swagger through the browser to use the services provided.
		http://localhost:8080/swagger-ui.html
	
	# Or call the services below through your browser.
	
		1. Returns all the worst movies
			URL: http://localhost:8080/v1/worst-movies
		2. Returns the producer with the longest gap between two consecutive awards and the producer with the fastest two awards.
			URL: http://localhost:8080/v1/worst-movies/minimum-maximum-ranges
		3. Returns the producer with the longest interval between two consecutive awards or the one with the fastest two awards, depending on the parameter passed in the request.
			URL: http://localhost:8080/v1/worst-movies/range/{range}
			Exemplos:
				http://localhost:8080/v1/worst-movies/range/MINIMUM
				http://localhost:8080/v1/worst-movies/range/MAXIMUM

---

Developed by Julio dos Santos
