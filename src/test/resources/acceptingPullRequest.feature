Feature: Merging Pull Request
#  Unfortunally due to lack of time, I don't care about PR conflict, so only flawlesly great PRs will work out

  Background:
    Given User is logged in to github

  Scenario: Successfully merges pull request
    Given there is a pull request pending
    And pull request is able to be merged
    When user click merge pull request
    And user click confirm message
    Then user see a message that pull request has been merged
    And user deletes a branch to clean up