Feature: Creating new Github repository

  Background:
    Given User has logged in

  Scenario: Successfully creates an empty github repository
    Given user clicks new repository button
    And user inserts repository name
    When user clicks create repository
    Then user is redirected to new repository page