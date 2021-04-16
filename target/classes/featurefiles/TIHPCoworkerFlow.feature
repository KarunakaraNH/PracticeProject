Feature: As a Coworker I should able to Validate the Design sent for Validation

  
  Scenario: Login to IHP as a coworker and Validate the design
 			Given Access IHP Planner for coworker validation 
  		When Click on login link on hoomepage
  		And Enter coworker username and Password and click on login button
  		When Click on Open Validation link
  		Then Enter Email Id and click on Get Design button
  		And Verify that designs are displayed in the drop down
  		When select any design and click on start validation button
  		Then Design area should be displayed and verify the login
  		When Modify the design and save the design
  		And Click on Finish Validation button
  		Then verify that Design is Validated 