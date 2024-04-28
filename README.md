# Shares Market Service

This is a Spring Boot application for managing shares in a stock market. It allows users to buy and sell shares and handles the operations accordingly.

## Features

- Buying and selling shares
- Integration with RabbitMQ for handling market events

## Getting Started

To get started with this project, follow these steps:

1. **Clone the repository**

   ```bash
   git clone https://github.com/renvema/shares-market-service.git

2. **Build the project**

   ```bash
   cd shares-market-service
mvn clean package
   
3. **Run Docker Compose**

   ```bash
   docker-compose up

4. Access the applicationThe application will be available at http://localhost:8080


## Valid JSON Request

Here is an example of a valid JSON request for buying shares:

```json
{
  "username": "kevin.evib",
  "ticker": "APPL",
  "operation": "BUY",
  "amount": 200
}