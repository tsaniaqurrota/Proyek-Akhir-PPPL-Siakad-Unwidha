Feature: delete data dosen
  Scenario: delete data dosen success
    Given User is logged in and on the matkul page to delete data matkul
    When User navigates to matkul details and deletes the data matkul
    Then User should see a success alert and click OK to delete data matkul
