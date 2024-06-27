Feature: update data dosen
  Scenario: update data dosen success
    Given User login to tambah data matkul page to update data matkul page
    When User submits valid credentials and navigates to update data matkul form
    And User enters valid data and submits the update form
    Then User should see a success alert and click OK from update
