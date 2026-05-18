# Online Banking System Application

A multi-tier online banking microservices application built with Java, deployed on Google Kubernetes Engine (GKE). Built for COE692 - Microservices & Cloud Architecture at Toronto Metropolitan University.

## Overview

This project implements a full-stack online banking system using a microservices architecture. Services are containerized with Docker and orchestrated on GKE, with MySQL as an isolated database service and KubeMQ handling asynchronous inter-service messaging.

## Features

- RESTful banking API built with Java JAX-RS on Apache Tomcat
- Core account management and transaction endpoints
- Containerized microservices with Docker
- Cloud deployment on Google Kubernetes Engine (GKE)
- Isolated MySQL database service
- Asynchronous inter-service messaging via KubeMQ
- Environment variable configuration for production-grade deployment

## Tech Stack

- **Language:** Java
- **Framework:** JAX-RS (Jersey)
- **Server:** Apache Tomcat
- **Database:** MySQL
- **Containerization:** Docker
- **Orchestration:** Kubernetes / GKE
- **Messaging:** KubeMQ
- **Version Control:** Git
