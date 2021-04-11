**BaseService:**

Has two methods RequestSpec builder and ResponseSpec builder. DRY the code nicely in one place.

**Response spec verifies**

Status code is 200 and content type is json Status code, also matches

Status 304 not modified and 422 Unprocessed entity and 503 service unavailable.

**Request spec verifies**

Sets the baseuri, basepath and provides PAT for oauth2 verification

It also has getter and setter for basepath.

**LeadService:**

It extends BaseService and provide implementation for requests.

Sets basepath /leads

**createNewLead** POST request with payload json file.

**updateLeadAddress** PUT request with payload json file and pathparam

**checkLeadExists** GET request with pathparam

**checkLeadAddress** GET request with patahparam

**getAllLeads** GET request

**LeadServiceTest:**

1- **testCreateNewLead** This POST request creates a new lead and set the setter.

2- **testCheckleadExists** This GET request confirms the lead we created earlier exists.

3- **testAddressIsNull** This GET request confirms the lead we created has address set to null.

4- **testUpdateLeadAddress** This PUT request updates the address which was null earlier.

5- **testGetAllLeads** This GET request prints total number of leads exists.

**Test Strategy**: Using Rest Assured make sure I can test API easily in short amount of time.
I have used Jsonpath to parse json response.

**Single Responsibility** Each test has single responsibility and include assertion(s).

**Re-usabiity** - Base service can be extended for other endpoints

**Maven** - Build tool

**TestNG** - Testing Framework 

**Rest Assured** - Automation testing tool for rest api endpoints. 

**log4j** - printing info

**testng.xml** - Test runner for our test class

**Run from Command line**

We can kick our test suite from command prompt, from project root run below maven command 

>mvn clean test