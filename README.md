# Skill Bartering Network

A platform where people exchange skills and knowledge without money, using a time-credit system for balanced trades.

## Overview

The Skill Bartering Network enables users to trade their expertise directly. For example, a web developer can exchange programming 
lessons for guitar lessons, creating a skill-based economy where expertise is the currency.

## Features

- **Skill Exchange**: Direct 1-to-1 skill bartering
- **Time Credits**: Balance unequal exchanges with time-based credits
- **Skill Matching**: Algorithm to find compatible skill exchanges
- **Verification System**: Portfolio and certification-based skill verification
- **Review System**: Build trust through peer reviews
- **Real-time Messaging**: Coordinate exchanges through in-app messaging

## Tech Stack

### Backend
- Java 11
- Spring Boot 2.7.14
- Spring Data JPA
- Spring Security
- PostgreSQL
- Redis
- Docker

### Frontend
- Angular 16
- TypeScript
- Angular Material
- RxJS

### Infrastructure
- Docker & Docker Compose
- Maven
- Git

## Getting Started

### Prerequisites
- Java 11
- Node.js 18+
- Docker Desktop
- Maven 3.6+

### Backend Setup

1. Start the database services:
```bash
cd infrastructure
docker-compose -f docker-compose.dev.yml up -d

