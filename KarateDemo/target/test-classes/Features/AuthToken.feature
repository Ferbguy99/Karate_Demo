@Auth
Feature: To Get Auth Token
  Background:
    * url baseUrl


  Scenario: To view all the booking IDs
    Given path auth
    And request {username: auth_username, password: auth_password}
    When method Post
    Then status 200 OK
    * eval karate.write(response, "token.json");