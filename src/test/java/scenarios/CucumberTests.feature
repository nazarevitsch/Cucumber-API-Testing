@ApiTESTING
  Feature: Api testing with help of Cucumber

    Scenario: Login with valid data
      Given user try to login with next data:
      | username       | password |
      | test@gmail.com | Test2021 |
      When user try to login
      Then user receive status code 200



    Scenario: Login with invalid data
      Given user try to login with next data:
        | username       | password  |
        | test@gmail.com | Nazar2021 |
      When user try to login
      Then user receive status code 406



    Scenario: Registration with already used data
      Given user try to register with next data:
        | username       | password  | name    |
        | test@gmail.com | Nazar2021 | Nazarii |
      When user try to register
      Then user receive status code 406



    Scenario: Create Producer
      Given user try to login with next data:
        | username       | password  |
        | test@gmail.com | Test2021  |
      When user try to login
      Given user try to create producer with next data:
        | image     | new_image | producer_name | types | steps | region_id | description              |
        | undefined | false     | Test producer | 1     | 1     | 1         | Test test test test test |
      When user try to create producer
      Then user receive status code 200



    Scenario: Create Producer with unauthorized user
      Given user try to create producer with next data:
        | image     | new_image | producer_name | types | steps | region_id | description              |
        | undefined | false     | Test producer | 1     | 1     | 1         | Test test test test test |
      When unauthorized user try to create producer
      Then user receive status code 200



    Scenario: Get producer
      Given producer id is 5
      When user try to get producer by id
      Then user receive status code 200
      And response don't equal zero