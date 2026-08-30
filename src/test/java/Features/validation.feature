Feature: Creating Alert

Feature: Selecting multiple products from Amazon

  @testone
  Scenario Outline: Selecting multiple products and ordering
    Given user logs in with credentials "<Username>" and "<Password>"
    And user is on home page
    And user searches for product "Trimmer For men"
    And user adds "<Productname>" to the cart
    And validate "<Productname>" is added to cart
    When user clicks buy the product
    Then product should be ordered successfully
    And validate Order ID is generated
    And user logs out from application

    Examples:
      | Username | Password | Productname |
      | Gowtham  | password | Philips     |
      | Gowtham  | password | Bombay Shaving Company     |
      | Gowtham  | password | Morphy Richards Kingsman     |

