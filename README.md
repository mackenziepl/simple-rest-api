# Recruiting task - "Simple API"

## What you'll build
- A simple Spring Boot REST application with H2

## Stack
- Java 11
- Spring Boot 2+
- Hibernate
- H2
- Maven

## Run
- Access to http://localhost:8080/api/users/{login}
- Database H2 - http://localhost:8080/h2-console



Example endpoint:
GET - http://localhost:8080/api/users/octocat

{
"id": 583231,
"login": "octocat",
"name": "The Octocat",
"type": "User",
"avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4",
"created_at": "2011-01-25T18:44:36.000+00:00",
"calculations": 0.015197568389057751
}