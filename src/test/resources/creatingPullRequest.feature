Feature: Creating Pull Request
#  Unfortunally due to lack of time, it's crucial to have cleaned repo from other PR and branche =(

  Background:
    Given User is logged in on github

  Scenario: Successfully creates a pull request
    Given there is following repo and branch on users github
    When user goes to the given branch repo page
    And clicks compare and create pull request
    Then user is redirected to the next step
    And user click create pull request button
    And user is redirected to pull requets page