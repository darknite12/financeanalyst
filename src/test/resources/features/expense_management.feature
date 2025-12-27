Feature: Expense Category Management
  As a user
  I want to manage expense categories
  So that I can categorize my transactions as various types of expenses

  Background:
    Given the Family Financial Analyst Application is running

  Scenario: Create a new expense category
    When an Expense is created as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |
    Then searching for Expense with Name "Costco" should return as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |
    And searching for Expense with Category "Groceries" should return as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |

  Scenario: Create multiple expense categories
    When Expenses are created as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |
      | Telus  | Bills     | TELUS                 |
      | Esso   | Gas       | ESSO GEORGETOWN #361  |
    Then searching for Expense with Name "Costco" should return as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |
    And searching for Expense with Name "Telus" should return as follows:
      | Name  | Category | Search String |
      | Telus | Bills    | TELUS         |
    And searching for Expense with Name "Esso" should return as follows:
      | Name | Category | Search String        |
      | Esso | Gas      | ESSO GEORGETOWN #361 |

  Scenario: Updating a search string of an existing expense
    Given an Expense is created as follows:
      | Name      | Category  | Search String          |
      | Starbucks | Fast Food | STARBUCKS COFFEE #5642 |
    When the Search String is updated to "STARBUCKS COFFEE #5642, STARBUCKS COFFEE #444"
    Then searching for Expense with Name "Starbucks" should return as follows:
      | Name      | Category  | Search String                                     |
      | Starbucks | Fast Food | STARBUCKS COFFEE #5642, STARBUCKS COFFEE #444 |

  Scenario: Delete an expense category
    Given an Expense is created as follows:
      | Name        | Category  | Search String     |
      | Tim Hortons | Fast Food | TIM HORTONS #1866 |
    When the Expense with Name "Tim Hortons" is deleted
    Then searching for Expense with Name "Tim Hortons" should return as follows:
      | Name | Category | Search String |

  Scenario: Delete an expense category with associated transaction
    Given an Expense is created as follows:
      | Name      | Category  | Search String          |
      | Starbucks | Fast Food | STARBUCKS COFFEE #5642 |
    And the Transaction is categorized as an Expense as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense   | Is Tracked |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-15       |        | Starbucks | true       |
    When the Expense with Name "Starbucks" is deleted
    Then searching for Expense with Name "Starbucks" should return as follows:
      | Name | Category | Search String |
    And searching for Transaction with Description "STARBUCKS COFFEE #5642" should return as follows
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-15       |        |         | false      |

  Scenario: List all expense categories
    Given Expenses are created as follows:
      | Name   | Category   | Search String     |
      | Costco | Groceries  | COSTCO WHOLESALE  |
      | Rogers | Phone Bill | ROGERS COMMUNICAT |
      | Shell  | Gas        | SHELL             |
    Then searching for all Expenses should return as follows:
      | Name   | Category   | Search String     |
      | Costco | Groceries  | COSTCO WHOLESALE  |
      | Rogers | Phone Bill | ROGERS COMMUNICAT |
      | Shell  | Gas        | SHELL             |

  Scenario: Find expenses by category
    Given Expenses are created as follows:
      | Name           | Category  | Search String |
      | Costco         | Groceries | COSTCO        |
      | Farmers Market | Groceries | FARMERS       |
      | Shell          | Gas       | SHELL         |
    Then searching for Expenses with Category "Groceries" should return as follows:
      | Name           | Category  | Search String |
      | Costco         | Groceries | COSTCO        |
      | Farmers Market | Groceries | FARMERS       |
    And searching for Expenses with Category "Gas" should return as follows:
      | Name  | Category | Search String |
      | Shell | Gas      | SHELL         |
