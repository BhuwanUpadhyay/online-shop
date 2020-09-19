Feature: E2E Test - Product

  Background:
    * url baseUrl

  Scenario: BAD - On Create

  # POST
    Given path '/products'
    And request
    """
      {
        "name" : "",
        "description" : ""
      }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 400
    And match header Content-Type == 'application/problem+json'

  Scenario: BAD - On Update
    * call read('create.feature') { count: 100 }
    * def id = $.id

  # PATCH
    Given path '/products/',id
    And request
    """
      {
        "name" : "",
        "description" : ""
      }
    """
    And header Accept = 'application/json'
    When method PATCH
    Then status 400
    And match header Content-Type == 'application/problem+json'

  Scenario: BAD - On Delete

  # DELETE
    Given path '/products/not_exist_id'
    When method DELETE
    Then status 400
    And match header Content-Type == 'application/problem+json'

  Scenario: BAD - On Get By Id

  # GET
    Given path '/products/not_exist_id'
    When method GET
    Then status 400
    And match header Content-Type == 'application/problem+json'

  Scenario: NOT_FOUND - On Update
  # PATCH
    Given path '/products/b87b3a25-4387-48df-b090-1b25379df3b0'
    And request
    """
      {
        "name" : "Check",
        "description" : "Check"
      }
    """
    And header Accept = 'application/json'
    When method PATCH
    Then status 404
    And match header Content-Type == 'application/problem+json'

  Scenario: NOT_FOUND - On Delete

  # DELETE
    Given path '/products/b87b3a25-4387-48df-b090-1b25379df3b0'
    When method DELETE
    Then status 404
    And match header Content-Type == 'application/problem+json'

  Scenario: NOT_FOUND - On Get By Id

  # GET
    Given path '/products/b87b3a25-4387-48df-b090-1b25379df3b0'
    When method GET
    Then status 404
    And match header Content-Type == 'application/problem+json'

  Scenario: OK - API

  # POST
    Given path '/products'
    And request
    """
      {
        "name" : "Ear Max 2020",
        "description" : "noise cancellation earphone"
      }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 201
    And match header Content-Type == 'application/json;charset=utf-8'
    And match $.id != null
    * def id = $.id

  # GET by id
    Given path '/products/',id
    When method GET
    Then status 200
    And match header Content-Type == 'application/json;charset=utf-8'
    And match $.id == id
    And match $.name == 'Ear Max 2020'
    And match $.description == 'noise cancellation earphone'

  # PUT
    Given path '/products/',id
    And request
    """
      {
        "name" : "Ear Max 2021",
        "description" : "Noise cancellation earphone"
      }
    """
    And header Accept = 'application/json'
    When method PATCH
    Then status 200
    And match header Content-Type == 'application/json;charset=utf-8'
    And match $.id == id
    And match $.name == 'Ear Max 2021'
    And match $.description == 'Noise cancellation earphone'

  # GET
    Given path '/products'
    When method GET
    Then status 200
    And match header Content-Type == 'application/json;charset=utf-8'
    And match header X-Total-Count == '1'
    And match header X-Result-Count == '1'
    And match $[0].id == id
    And match $[0].name == 'Ear Max 2021'
    And match $[0].description == 'Noise cancellation earphone'

  # DELETE
    Given path '/products/',id
    When method DELETE
    Then status 204

  Scenario: OK - Pagination
    * def fun = function(x){ return { count: x } }
    * def data = karate.repeat(17, fun)
    * call read('create.feature') data

  # GET
    Given path '/products'
    And param offset = 1
    And param limit = 5
    When method GET
    Then status 200
    And match header Content-Type == 'application/json;charset=utf-8'
    And match header X-Total-Count == '17'
    And match header X-Result-Count == '5'
    And match $[0].name == 'name0'
    And match $[0].description == 'description0'
    And match $[4].name == 'name4'
    And match $[4].description == 'description4'
