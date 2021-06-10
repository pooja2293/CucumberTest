@first
Feature: GIT functionality

Scenario: Login Scenario
#EXAMPLES
Given user is already on github page
When user clicks on sign in button
Then user get sign in 
Then user creates repo by name
| Demo |
Then user created private repo
Then user successfully created repo

