
Feature: Purchase an item from ecommerce website

Background:
Given User is landed on ecommerce application

@Regression
Scenario Outline:
Sumbitting order page confirmation

Given user logged in with username <username> and password <password>
When user adds product <productName> to the cart
And user checkout the product <productName> and submit the order
Then "Thankyou for the order." message is displayed on ConfirmationPage

Examples:
|	username				|	password			|	productName		|
|	girirohi2@gmail.com		|	Girinath@2003		|	Zara coat 3		|
