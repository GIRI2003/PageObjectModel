Feature: Error Validation

@ErrorValidation
Scenario Outline:
Error Validation with incorrect username and password

Given User is landed on ecommerce application
When user logged in with username <username> and password <password>
Then "Incorrect email or password." error message should display

Examples:
|	username				|	password			|
|	girqwohi2@gmail.com		|	Girinatsd@2003		|


