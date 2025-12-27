Feature: Income Category Management
  As a user
  I want to manage income categories
  So that I can categorize my transactions as various types of income

  Background:
    Given the Family Financial Analyst Application is running

  Scenario: Create a new income category
    When an Income is created as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    Then searching for Income with Name "Salary" should return as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    And searching for Income with Category "Payroll" should return as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |

  Scenario: Create multiple income categories
    When Incomes are created as follows:
      | Name          | Category     | Search String |
      | Salary        | Payroll      | ACME CORP     |
      | Tax Refund    | Income Taxes | CRA REFUND    |
      | Birthday Gift | Gift         | E-TRANSFER    |
    Then searching for Income with Name "Salary" should return as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    And searching for Income with Name "Tax Refund" should return as follows:
      | Name       | Category     | Search String |
      | Tax Refund | Income Taxes | CRA REFUND    |
    And searching for Income with Name "Birthday Gift" should return as follows:
      | Name          | Category | Search String |
      | Birthday Gift | Gift     | E-TRANSFER    |

  Scenario: Updating a search string of an existing income
    Given an Income is created as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    When the Search String is updated to "ACME CORP, ACME CORPORATION"
    Then searching for Income with Name "Salary" should return as follows:
      | Name   | Category | Search String               |
      | Salary | Payroll  | ACME CORP, ACME CORPORATION |

  Scenario: Delete an income category
    Given an Income is created as follows:
      | Name | Category | Search String |
      | Gift | Gift     | E-TRANSFER    |
    When the Income with Name "Gift" is deleted
    Then searching for Income with Name "Gift" should return as follows:
      | Name | Category | Search String |

  Scenario: Delete an income category with associated transaction
    Given an Income is created as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
    And a Transaction is imported as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       |        |         | false      |
    And the Transaction is categorized as an Income as follows:
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       | Salary |         | true       |
    When the Income with Name "Salary" is deleted
    Then searching for Income with Name "Salary" should return as follows:
      | Name | Category | Search String |
    And searching for Transaction with Description "ACME CORP" should return as follows
      | Account Type     | Description | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Chq | ACME CORP   | 2500.00    | 2024-01-15       |        |         | false      |

  Scenario: List all income categories
    Given Incomes are created as follows:
      | Name       | Category     | Search String |
      | Salary     | Payroll      | ACME CORP     |
      | Tax Refund | Income Taxes | CRA REFUND    |
      | Gift       | Gift         | E-TRANSFER    |
    When searching for All Incomes
    Then Incomes should return as follows:
      | Name       | Category     | Search String |
      | Salary     | Payroll      | ACME CORP     |
      | Tax Refund | Income Taxes | CRA REFUND    |
      | Gift       | Gift         | E-TRANSFER    |

  Scenario: Find incomes by category
    Given Incomes are created as follows:
      | Name          | Category | Search String |
      | Birthday Gift | Gift     | E-TRANSFER    |
      | Holiday Gift  | Gift     | GIFT          |
      | Salary        | Payroll  | ACME CORP     |
    When searching for Incomes with Category "Gift" should return as follows:
      | Name          | Category | Search String |
      | Birthday Gift | Gift     | E-TRANSFER    |
      | Holiday Gift  | Gift     | GIFT          |
    And searching for Incomes with Category "Payroll" should return as follows:
      | Name   | Category | Search String |
      | Salary | Payroll  | ACME CORP     |
