Feature: Pushing a commit with sample file

  Background:
    Given User has logged in on github

  Scenario: Successfully pushes files to existing git repository
    Given user creates a working branch
    And user adds a sample file
    And user commits the file to the index
    When user pushes commit
    Then commit is visible on github repo page