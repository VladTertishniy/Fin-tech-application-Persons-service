<h1 align="center"> Persons service </h1> <br>

<p align="center">
  Service have the ability to create a persona.
  A person is an abstract entity. One of the implementations of the persona is an operator - a store employee.
  Persona have common fields such as name, date of birth.
  The operator completes the persona with fields: sell point identifier, taxpayer number.
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [API](#requirements)
- [Acknowledgements](#acknowledgements)




## Introduction

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e91606af4a364076a7058c5ea1c006a8)](https://www.codacy.com/app/joneubank/microservice-template-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=overture-stack/microservice-template-java&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/gh/overture-stack/microservice-template-java/tree/master.svg?style=shield)](https://circleci.com/gh/overture-stack/microservice-template-java/tree/master)

## Features
This service based on Spring Boot 2.7.4 and java 17. 
Use Feign Client for integration with other services, AWS RDS as DB, Hibernate as ORM, Lombok annotations, Mapstruct for entity mapping,
WebSocket and eureka


## Requirements
The application can be run locally or in a docker container, the requirements for each setup are listed below.


### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)
* [AWS RDS](https://us-east-1.console.aws.amazon.com/rds/home?region=us-east-1#)


### Docker
* [Docker](https://www.docker.com/get-docker)
* [AWS RDS](https://us-east-1.console.aws.amazon.com/rds/home?region=us-east-1#)


## Quick Start

Before service starting need create AWS RDS Postgres instance
In __application.properties__ need fill below properties
`spring.datasource.url`
`spring.datasource.username`
`spring.datasource.password`

### Run Local
```bash
$ mvn spring-boot:run
```

Application will run by default on port `9083`
Configure the port by changing `server.port` in __application.properties__


### Run Docker

First build the image:
```bash
$ docker-compose build
```

When ready, run it:
```bash
$ docker-compose up
```

## API
### Operator
For Operator creation
**Post** {host}:{port}/operators/create
<details><summary>Body example</summary>
{
    "name":"name",
    "dateOfBirth":"1995-02-05T12:59:11.332",
    "sellPointIdentifier":"2",
    "taxpayerNumber":"1543233"
}
</details>

For Operator updating
**Post** {host}:{port}/operators/update/{id}
<details><summary>Body example</summary>
{
    "name":"name",
    "dateOfBirth":"1995-02-05T12:59:11.332",
    "sellPointIdentifier":"2",
    "taxpayerNumber":"1543233"
}
</details>

For Operator deleting
**Get** {host}:{port}/operators/delete/{id}

For Operator get by id
**Get** {host}:{port}/operators/getById/{id}

For Operator get all
**Get** {host}:{port}/operators/getAll

For Operator get all on page
**Get** {host}:{port}/operators/getAllOnPage

## Acknowledgements
Acknowledgements for Yevhenii Khudolii for interesting trainig project and support =)
