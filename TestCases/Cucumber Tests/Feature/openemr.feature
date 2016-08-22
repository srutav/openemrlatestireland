Feature:Open EMR

Scenario:Login, Add and Search Patients
Given browser is launched proper and login is done
And We add patient
When Insurance Provider is present or we add Insurance provider
And We search patient
When Patient is present in db
Then Scenario is completed







