Feature: Tambah data matkul
  Scenario: Tambah data matkul
    Given User login to tambah data matkul page
    When User submits valid credentials and navigates to tambah data matkul form
    And User enters valid data and submits the form
    Then User should see a success alert and click OK
