Feature: E2E Test - Product - Create

  Background:
    * url baseUrl

  Scenario: Create Product

  # POST
    Given path '/products'
    * def newProduct =
    """
      { "name" : "" , "description" : "" }
    """
    * set newProduct.name = "name" + count
    * set newProduct.description = "description" + count
    And request newProduct
    And header Accept = 'application/json'
    When method POST
    Then status 201
