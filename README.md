[![CircleCI](https://circleci.com/gh/renayearnshaw/food-hygiene-ratings.svg?style=svg)](https://circleci.com/gh/renayearnshaw/food-hygiene-ratings)
# FoodHygieneRatings
The __FoodHygieneRatings__ project contains two endpoints:
1. <http://localhost:8080/foodhygiene/authorities>
2. <http://localhost:8080/foodhygiene/authorities/{authorityId}/ratings/summary>

### Usage
To run the application in the _Spring Tool Suite_ IDE:
1. In the _Package Explorer_ view, select the _FoodHygieneRatingsApplication_ class.
2. Right click and select _Run As->Spring Boot App_

This will allow you to access the following two endpoints via a web browser:
1. <http://localhost:8080/foodhygiene/authorities>
<p>This endpoint returns a list of local authorities in a JSON format.
<p>Each authority entry contains the name and id of the authority.
<p>This list can be used by the caller to populate a listbox with the names of the authorities. When the user selects a name, the corresponding _LocalAuthorityId_ can be sent to the next endpoint.
2. <http://localhost:8080/foodhygiene/authorities/{LocalAuthorityId}/ratings/summary>
<p>This endpoint returns a list of ratings summaries in a JSON format.
<p> Each rating summary entry contains a description of the rating, and a count of the number of establishments in this authority that achieved this rating, as a percentage.
<p>This list can be used by the caller to populate a table.

### Tests
I've only included a few unit test for demonstration purposes.
I'm not that familiar with JUnit (I use Spock mostly) so they're pretty basic.

#### Still to be done...
##### Authority
1. Change the output name for the _LocalAuthorityID_ property (using Jackson).

##### Rating
1. Read ratings "Pass" and "Pass and Eat Safe" into the single enum value of *Rating.PASS*
2. Read ratings "AwaitingInspection" and "Awaiting Inspection" into the single enum value of *Rating.AWAITING_INSPECTION*
3. Provide different names for the enum values on serialization/deserialization by using Jackson rather than the _toString()_ method

##### AuthoritiesService & EstablishmentsService
1. Inject headers and URI via configuration (_application.properties_ file?). Will this allow me to remove the _AuthoritiesService(String, String)_ constructor needed by the unit tests?

##### RatingsService
1. Order the rating summaries using the order of the _Rating_ enum