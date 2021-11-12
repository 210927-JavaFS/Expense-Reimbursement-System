# Employee Reimbursement System (ERS)

## Overview
- The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.
- The back-end system shall use Hibernate to connect to an AWS RDS Postgres database. The middle tier shall use Javalin technology for dynamic Web application development. The front-end view shall use HTML/JavaScript to make an application that can call server-side components RESTfully. Passwords shall be encrypted in Java and securely stored in the database. The middle tier shall follow proper layered architecture, have reasonable (~70%) test coverage of the service layer, and implement Logback for appropriate logging. 

## Features Implemented
- Able to login to system as either an employee or manager
- Employee can create and submit new reimbursement requests
- Employee can view their own past reimbursement requests
- Manager can view all past reimbursement requests by all employees
- Manager can filter requests by status
- Manager can approve or deny any pending request

## Technologies Used
- Java
- SQL
- CSS
- HTML
- JavaScript
- JUnit
- Hibernate
- AWS RDS
- Log4J
- Javalin

## How To Set Up
1. Clone project onto local machine using git clone https://github.com/210927-JavaFS/Expense-Reimbursement-System.git
2. Navigate to src/main/resources/hibernate.cfg.xml and configure database connection credentials
3. Run the Driver class as a Java application
4. Navigate to http:localhost:8081
## Usage
1. Register new users on the page or instantiate them directly in the database
2. Login on page with made credentials
3. Use application to request reimbursements and then approve/deny them as a manager
