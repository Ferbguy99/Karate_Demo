@redirectDisable
Feature: Testing Redirect Url
  
  Background:
    * configure followRedirects = false

    Scenario:
#      Given url microsoftHomeUrl
#      When method GET
#      Then status 302
##      * print responseHeaders
#      And match responseHeaders.Location[0] == microsoftHomeUrl + "/en-in/"
#      * def redirectUrl = responseHeaders.Location[0]
#      Given url redirectUrl
#
#      When method GET
#      Then status 200 OK
      * def response = { foo: [ 'a', 'b' ] }
      * match response contains only deep { foo: [ 'b', 'a' ] }