# FoodHygieneRatings

[![CircleCI](https://circleci.com/gh/renayearnshaw/food-hygiene-ratings.svg?style=svg)](https://circleci.com/gh/renayearnshaw/food-hygiene-ratings)
[![codecov](https://codecov.io/gh/renayearnshaw/food-hygiene-ratings/branch/master/graph/badge.svg)](https://codecov.io/gh/renayearnshaw/food-hygiene-ratings)

The __FoodHygieneRatings__ project is written using SpringBoot and Thymeleaf.
<br>It contains two endpoints:
1. <http://localhost:8080/foodhygiene/authorities>
2. <http://localhost:8080/foodhygiene/authorities/{authorityId}/ratings>

<p>The first endpoint displays a list of all the local authorities in England, Wales and Northern Ireland.
(At the moment I only show the first twenty, to speed up the application - this will change in time)
<p>By clicking on the <i>View</i> link for an authority, you are redirected to the second endpoint, 
with the <i>authorityId</i> set to that of the authority you selected.
This endpont summarizes the food safety ratings achieved by the businesses within the authority.
Each rating category is shown, along with the percentage of premises that achieved that rating. 

### To run the application

#### In the _IntelliJ IDEA_ IDE
1. In the _Package Explorer_ view, select the _FoodHygieneRatingsApplication_ class.
2. Right click and select _Run As->Spring Boot App_

#### Using Docker
1. Pull the image from Docker Hub using the following command:
<br>``docker pull renayearnshaw/rating-summary``
2. Run the application using:
<br>``docker run -d -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=production" renayearnshaw/rating-summary``

### Miscellaneous 
I’ve implemented continuous integration for this project using _CircleCI_. Whenever a change is pushed to the repository, _CircleCI_ builds and tests the code. The status of the latest cycle is reflected in the _CircleCI_ badge displayed above.
<br>Code coverage is implemented using _CodeCov_ as part of the _CircleCI_ build. The latest coverage percentage is displayed in the _CodeCov_ badge displayed above.
