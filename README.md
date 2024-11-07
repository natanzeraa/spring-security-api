# Spring Security Authentication API Boilerplate

## Project Description
This is an authentication API developed in **Spring Boot** using **Spring Security**, **Spring Data**, **Docker**, **MySQL**, and **JWT**. The project aims to provide an authentication boilerplate with three levels of user permissions:

- **ADMIN**: Can register new users and has all privileges.
- **USER**: Can view documents and upload files.
- **VIEWER**: Can only view documents.

## Technologies Used
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

## Navigation
- [Running the project](#running-the-project)
- [Authentication Endpoints](#authentication-endpoints)
- [Request and Response Examples](#request-and-response-examples)


### Running the project
To run this project locally, you can use Docker to set up the MySQL database and phpMyAdmin for database management. Follow these steps:

1. Make sure Docker is installed and running on your machine.
2. Use the `docker-compose` file below to start the necessary services.
3. Run the following command to start the services: <u>docker-compose up --build</u>
4. Access phpMyAdmin at http://localhost:8090 to manage your database.
5. Uncomment and configure the springboot service in docker-compose.yml if you want to run the Spring Boot application within Docker.

---

## Authentication Endpoints

### POST /auth/hash-generator
Generates a password hash for manual insertion into the database. The password must not be plain text.

### POST /auth/register
Registers a new user in the application. Only users with the **ADMIN** role can access this endpoint.

### POST /auth/login
Performs login and generates a JWT token for authentication.

### POST /documents
Allows users with **ADMIN** and **USER** roles to upload documents.

### GET /documents
Allows users with any role to view documents.

## Request and Response Examples

### Request for `/auth/register`
```json
{
    "login": "admin@gmail.com",
    "password": "admin",
    "role": "ADMIN"
}
```
### Response for `/auth/register`

```json
{
    "id": "acc75955-3eed-414c-8378-b87cec3d865d",
    "login": "admin@gmail.com",
    "password": "$2a$10$5Wv59KbngHg5RSsThRiVpOpbO723D.Sw/NJmqoYvIo9ChWCIjASwq",
    "role": "ADMIN",
    "username": "admin@gmail.com",
    "authorities": [
        {
            "authority": "ROLE_ADMIN"
        },
        {
            "authority": "ROLE_USER"
        }
    ],
    "enabled": true,
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
}
```
### Request for `/auth/register` (Regular user)

```json
{
    "login": "user@gmail.com",
    "password": "user",
    "role": "USER"
}
```

### Response for `/auth/register` (Regular user)

```json
{
    "id": "3670881c-632f-4853-9d52-ca00ed087d26",
    "login": "user@gmail.com",
    "password": "$2a$10$hqw.00RRjnv2Gr8mnU/tT.ebeABHy7HVLXdPncxuyb5k5IjhOvmym",
    "role": "USER",
    "username": "user@gmail.com",
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "enabled": true,
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
}
```
### Example Response for `/documents`

```json
[
    {
        "id": "0577c6e1-6e1d-49f0-baf1-4abfde02ec11",
        "title": "A user's resume",
        "url": "http://the-amazon-s3-storage-for-example/",
        "user": {
            "id": 1,
            "login": "admin@gmeil.com",
            "password": "$2a$10$GcRVRHfejk2WvON7ys.txuRBBlCU8TIlCW5pUNeXtNGR4GxnNiJBK",
            "role": "ADMIN",
            "authorities": [
                {
                    "authority": "ROLE_ADMIN"
                },
                {
                    "authority": "ROLE_USER"
                },
                {
                    "authority": "ROLE_VIEWER"
                }
            ],
            "username": "admin@gmeil.com",
            "accountNonExpired": true,
            "accountNonLocked": true,
            "credentialsNonExpired": true,
            "enabled": true
        }
    }
]
```
---

<div align="center">
    <h6>Author</h6>
    <img src="https://avatars.githubusercontent.com/u/172435339?v=4/150" alt="Author's Photo" style="border-radius: 50%; width: 80px; height:80px;">
    <h3>Natan Oliveira</h3>
    <h6>Backend Developer.</h6>
    <a href="https://www.linkedin.com/in/natan-oliveira-71023822b/">
        <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
    </a>
</div>
