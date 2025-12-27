Feature: Transaction Management
  As a user
  I want to manage financial transactions
  So that I can track and categorize my income and expenses

  Background:
    Given the Family Financial Analyst Application is running

  Scenario: Create a single transaction
    When a Transaction is created as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        |         | false      |
    Then searching for Transaction with Description "COSTCO WHOLESALE W162" should return as follows:
      | Account Type             | Description           | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162 | -125.50    | 2024-01-15       |        |         | false      |

  Scenario: Create multiple transactions
    When Transactions are created as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        |         | false      |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-16       |        |         | false      |
      | PC Financial Chq         | ACME CORP              | 2500.00    | 2024-01-17       |        |         | false      |
    Then searching for All Transactions should return 3 transactions

  Scenario: Categorize a transaction as expense at creation time
    Given an Expense is created as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |
    When a Transaction is created as follows:
      | Account Type             | Description           | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162 | -125.50    | 2024-01-15       |        | Costco     | true      |
    Then searching for Transaction with Description "COSTCO WHOLESALE W162" should return as follows:
      | Account Type             | Description           | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162 | -125.50    | 2024-01-15       |        | Costco  | true       |

  Scenario: Categorize a transaction as expense
    Given an Expense is created as follows:
      | Name   | Category  | Search String         |
      | Costco | Groceries | COSTCO WHOLESALE W162 |
    And a Transaction is created as follows:
      | Account Type             | Description           | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162 | -125.50    | 2024-01-15       |        | Costco     | true      |
    When the Transaction is categorized as an Expense as follows:
      | Account Type             | Description           | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162 | -125.50    | 2024-01-15       |        | Costco  | true       |
    Then searching for Transaction with Description "COSTCO WHOLESALE W162" should return as follows:
      | Account Type             | Description           | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162 | -125.50    | 2024-01-15       |        | Costco  | true       |

  Scenario: Categorize transaction as income at creation time
    Given an Income is created as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    When a Transaction is created as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       | Salary |         | true      |
    Then searching for Transaction with Description "ACME CORP" should return as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       | Salary |         | true       |

  Scenario: Categorize transaction as income
    Given an Income is created as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    And a Transaction is created as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       |        |         | false      |
    When the Transaction is categorized as an Income as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       | Salary |         | true       |
    Then searching for Transaction with Description "ACME CORP" should return as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       | Salary |         | true       |

  Scenario: List all untracked transactions
    Given Transactions are created as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        |         | false      |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-16       |        |         | false      |
      | PC Financial Chq         | ACME CORP              | 2500.00    | 2024-01-17       | Salary |         | true       |
    When searching for Transactions with Is Tracked "false"
    Then Transactions should return as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        |         | false      |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-16       |        |         | false      |

  Scenario: List all tracked transactions
    Given Transactions are created as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense   | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        | Costco    | true       |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-16       |        | Starbucks | true       |
      | PC Financial Chq         | ACME CORP              | 2500.00    | 2024-01-17       | Salary |           | true       |
    When searching for Transactions with Is Tracked "true"
    Then Transactions should return as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense   | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        | Costco    | true       |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-16       |        | Starbucks | true       |
      | PC Financial Chq         | ACME CORP              | 2500.00    | 2024-01-17       | Salary |           | true       |

  Scenario: Filter transactions by account type
    Given Transactions are created as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        |         | false      |
      | PC Financial Chq         | ACME CORP              | 2500.00    | 2024-01-16       |        |         | false      |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-17       |        |         | false      |
    When searching for Transactions with Account Type "PC Financial Master Card"
    Then Transactions should return as follows:
      | Account Type             | Description            | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | COSTCO WHOLESALE W162  | -125.50    | 2024-01-15       |        |         | false      |
      | PC Financial Master Card | STARBUCKS COFFEE #5642 | -5.50      | 2024-01-17       |        |         | false      |
