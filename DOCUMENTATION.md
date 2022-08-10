## ToDo API

Swagger:

> **http://localhost:8081/q/swagger-ui/**

List ToDo
> **GET: http://localhost:8081/to-do-list**

Add ToDo
> **POST: http://localhost:8081/to-do-list**

Edit ToDo
> **PUT: http://localhost:8081/to-do-list**

Remove ToDo
> **DELETE: http://localhost:8081/to-do-list**

Manage state by title
> **PUT: http://localhost:8081/to-do-list/manage-state/{title}**

Search by title
> **GET: http://localhost:8081/to-do-list/search/{title}**

## Configuration

Allow Cors

Lines added to the `application.properties` file.
> - quarkus.http.cors=true
> - quarkus.http.cors.origins=*
> - quarkus.http.cors.methods=GET, POST, PUT, DELETE
> - quarkus.http.cors.exposed-headers=Content-Disposition
