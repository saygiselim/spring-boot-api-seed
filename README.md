# Spring Boot API Seed

JWT based security implemented API starter project

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

* OpenJDK - [Download & Install](https://openjdk.java.net/install/)
* Docker - [Download & Install](https://www.docker.com/get-started)

### Installing

#### Setup Database
* run `compose.sh` shell script to build PostgreSQL and pgAdmin containers

```
$ cd database
$ ./compose.sh
```

* run `run.sh` shell script to start PostgreSQL and pgAdmin containers

```
$ cd database
$ ./run.sh
```

- examples above are given for `Linux/Unix` environment, for Windows use `.bat` versions of shell scripts

#### Access to PostgreSQL Server
* `localhost:5432`
* **Username:** postgres
* **Password:** password

#### Access to PgAdmin
* **URL:** `http://localhost:5050`
* **Username:** default@email.com
* **Password:** password

#### Add a new server in PgAdmin:
* **Host name/address:** `postgres`
* **Port:** `5432`
* **Username:** postgres
* **Password:** password

## Run Test

```
$ gradle test
```

## Run Build

with test
```
$ gradle clean build
```

without test
```
$ gradle bootJar
```

## Serve App on Apache Tomcat

run
```
$ gradle bootRun
```

or, after running build command run

```
$ java -jar build/libs/spring-boot-api-seed-1.0.0-RELEASE.jar
```

#### Access to API Resources
* API - http://localhost:8080/api
* Health Check - http://localhost:8080/actuator/health
* Swagger API Documentation - http://localhost:8080/swagger-ui.html

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - Web Framework
* [Gradle](https://gradle.org/) - Dependency Manager
* [PostgreSQL](https://www.postgresql.org/) - Open Source Relational Database
* [Docker](https://www.docker.com/) - Containerized Platforms

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 
