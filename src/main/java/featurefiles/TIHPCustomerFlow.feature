Feature: TIHP Customer flows
  As a logged in user I want to send design for Validation and cart
	
	
	Background:
	Given Access IHP Planner 
  		And Click on login link
  		When Enter username and Password and click on login button 
  		Then Verify that user is logged into planner
  		And add few cabinets to Planner and save the design
	
  Scenario: Send Design for Validation
  		Then Click on Proceed button and opt for Send validation option
  		And Verify that Design is sent for Validation
  		
  
  		
  

  
