Feature: Transaction Import
  As a user
  I want to import transactions from CSV files
  So that I can automatically load my banking data into the system

  Background:
    Given the Family Financial Analyst Application is running

  Scenario: Import PC Financial transactions with matching categories
    Given Expenses were created as follows:
      | Name  | Category        | Search String |
      | Apple | Technology      | Apple         |
      | Temu  | Online Shopping | Temu          |
    When PC Financial transactions are imported as follows:
      """
      "Description","Type","Card Holder Name","Date","Time","Amount"
      "APPLE.COM/BILL","PURCHASE","FRANCISCO","12/19/2025","11:37 PM","-67.79"
      "TEMU.COM","PURCHASE","ALICE","12/19/2025","12:09 PM","-68.43"
      """
    Then Transactions are created as follows:
      | Account Type             | Description    | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | APPLE.COM/BILL | -67.79     | 2025-12-19       |        |   Apple | true       |
      | PC Financial Master Card | TEMU.COM       | -68.43     | 2025-12-19       |        |    Temu | true       |

  Scenario: Import PC Financial transactions without matching categories
    Given Expenses were created as follows:
      | Name  | Category        | Search String |
      | Apple | Technology      | Apple         |
      | Temu  | Online Shopping | Temu          |
    When PC Financial transactions are imported as follows:
      """
      "Description","Type","Card Holder Name","Date","Time","Amount"
      "Amazon.ca*8S1SY8ON3","PURCHASE","FRANCISCO","12/19/2025","03:45 AM","-7.07"
      """
    Then Transactions are created as follows:
      | Account Type             | Description    | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | Amazon.ca*8S1SY8ON3 | -7.07     | 2025-12-19   |        |         | false      |

  Scenario: Import transactions with duplicate detection
    Given a Transaction was created as follows:
      | Account Type             | Description    | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | APPLE.COM/BILL | -67.79     | 2025-12-19       |        |         | false      |
    When PC Financial transactions are imported as follows:
      """
      "Description","Type","Card Holder Name","Date","Time","Amount"
      "APPLE.COM/BILL","PURCHASE","FRANCISCO","12/19/2025","11:37 PM","-67.79"
      """
    Then a duplicate Transaction with Description "APPLE.COM/BILL" should return as follows:
      | Account Type             | Description    | Amount CAD | Transaction Date | Income | Expense | Is Tracked |
      | PC Financial Master Card | APPLE.COM/BILL | -67.79     | 2025-12-19       |        |         | false      |

  Scenario: Import invalid PC Financial import
    When PC Financial transactions are imported as follows:
      """
      "Description","Type","Date","Amount"
      "APPLE.COM/BILL","PURCHASE","INVALID_DATE","-67.79"
      """
    Then the import should fail with error "Invalid date format"
    And no Transactions are created

  Scenario: Import empty PC Financial import
    When PC Financial transactions are imported as follows:
      """
      "Description","Type","Card Holder Name","Date","Time","Amount"
      """
    Then the import should fail with error "Empty Import file"
    And no Transactions are created