Anwsers:

	a) Examples of chaining using assertJ :
		- given3Employees_whengetAll_thenReturn3Records() which calls hasSize(), extract() and contains()
		- givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() which also uses the same calls as above
		- givenEmployees_whenGetEmployees_thenStatus200() which uses extract() and containExactly()
		- whenValidInput_thenCreateEmployee() which uses extract() and containsOnly()
	b) In EmployeeService_UNitTest we Mock the repository with out using the database, mimicking the return of the later
	c) A @Mock is a anotation used to simulate the responses of a certain component we dont want or cant instanciate, the @MockBean anotation takes this a level up and we start working in the spring world which means since most tests use a Spring test Context we can use the good things of spring boot like autowired and such since we are threating it as a component and not just a mocked instance
	d) THis files contains the properties to configure the percistance of data, when the tests are invocked we have this file override the properties file from the aplication so that we can have diferente configurations for tests