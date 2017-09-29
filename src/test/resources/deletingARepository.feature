Feature: Deleting repository

  Background:
    Given User is logged into github
    And repository exists

  Scenario: Successfully deletes github repository
    Given user is on repository settings page
    When user scrolls the page to the bottom
    And user sees delete the repository button
    And user puts repository name into dialog
    And user confirm deleting
    Then user is redirected delete repository page
    And user is asked for a password
    And user confirms the password
    Then user is redirected to git main page