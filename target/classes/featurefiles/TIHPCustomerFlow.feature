Feature: TIHP Customer flows
  As a logged in user I want to send design for Validation and cart

  Background: 
    Given Access IHP Planner
    And Click on login link
    When Enter username and Password and click on login button
    Then Verify that user is logged into planner

  Scenario: Send Design for Validation
    And add few cabinets to Planner and save the design
    Then Click on Proceed button and opt for Send validation option
    And Verify that Design is sent for Validation
    
  Scenario: Send Design to shopping Cart  
  	And add any cabinets to Planner and save the design
  	When Click on Proceed button and opt for Send to cart option
  	Then Verify that Design is sent to Direct Basket
  	
  Scenario: Verify Send Validation button is disabled for the design less than four Cabnet  
  	And add single cabinet to Planner and save the design
  	Then Click on Proceed button and Verify that Send Validation option is disabled with proper warning message	
  	
