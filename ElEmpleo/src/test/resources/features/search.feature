Feature: Search jobs on the page elempleo
		I want to find jobs whit any keyword
	
	Scenario: Search Successful
		Given I want to find jobs with a keyword	
		When I search for jobs containing contador
		Then I should only see items related to contador and save this in a .txt file