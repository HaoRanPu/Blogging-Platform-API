# [Blogging Platform API](https://roadmap.sh/projects/blogging-platform-api)

This project is a simple RESTful API for a personal blogging platform built using Java and Spring Boot. The API supports basic CRUD (Create, Read, Update, Delete) operations for managing blog posts.

## Features

- Create a new blog post
- Update an existing blog post
- Delete a blog post
- Retrieve a single blog post by ID
- Retrieve all blog posts
- Search blog posts by a term (title, content, or category)

## Technologies Used

- Spring Boot
- Java
- Maven
- Spring Data JPA
- Spring JDBC
- H2 Database
- Lombok
- Spring Boot DevTools
- JUnit & Spring Boot Test

## API Endpoints

### 1. Create Blog Post

- **URL**: `POST /posts`
- **Request Body Example**:
  ```json
  {
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post.",
    "category": "Technology",
    "tags": ["Tech", "Programming"]
  }
  ```
- **Response**:
  - **201 Created**: Returns the Created blog post.
  - **400 Bad Request**: Validation errors.

### 2. Update Blog Post

- **URL**: `PUT /posts/{id}`
- **Request Body**:
  ```
  {
    "title": "My Updated Blog Post",
    "content": "This is the updated content of my blog post.",
    "category": "Technology",
    "tags": ["Tech", "Programming"]
  }
  ```
- **Response**:
  - **200 OK**: Returns the updated blog post.
  - **400 Bad Request**: Validation errors.
  - **404 Not Found**: Blog post not found.

### 3. Delete Blog Post

- **URL**: `DELETE /posts/{id}`
- **Response**:
  - **204 No Content**: Blog post successfully deleted.
  - **404 Not Found**: Blog post not found.

### 4. Get a Single Blog Post

- **URL**: `GET /posts/{id}`
- **Response**:
  - **200 OK**: Returns the requested blog post.
  - **404 Not Found**: Blog post not found.

### 5. Get All Blog Posts

- **URL**: `GET /posts`
- **Optional Query Parameter**:
  - `term`: Search term to filter posts by title, content, or category.
- **Response**:
  - **200 OK**: Returns a list of blog posts.

## How to Run the Project

1. **Build the Project**:

   ```
   mvn clean install
   ```

2. **Run the Application**:

   ```
   mvn spring-boot:run
   ```

3. **Access the API**: By default, the application runs on `http://localhost:8080`. You can change the host and port by modifying the `application.properties` file in the `src/main/resources` directory. For example:
   ```
   server.host=0.0.0.0
   server.port=8081
   ```
4. **Access H2 Console** (for database inspection):

   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:blogdb`
   - Username: `sa`
   - Password: `password`
