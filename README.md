# TutorialsNinja_Assignment
It is built in TestNG Assertions Maven Project

Hi,

Create a test automation framework with below capabilities
1. Maven Project
2. Page Object Model
3. Page Factory
4. Configuration Files
5. Logs
6. Assertion

Steps to open product catalog page
1. Open link http://tutorialsninja.com/demo/index.php
Your Store
My Store
tutorialsninja.com
2. Click on MP3 Players
3. Click on Show All MP3 Players

Test-1
1. Add any two products for comparison on product page(Click Compare icon) e.g iPod Nano and iPod Classic
2. Verify successful message. Use Assertion. 
3. Verify Product Compare count should be changed from 0 to 2. Use Assertion.
4. Click on Product Compare. Compare details with details on product catalog- Products name, Unit Price. Use Assertion.

Test-2
1. Click on Add to cart
2. Verify successful message. Use Assertion.
3. Verify items count. Use Assertion. 
4. Verify added products should get added on items cart page. Compare details with details on product catalog- Products name, Unit Price, Quantity and Total price. Use Assertion.

Test-3
1. Remove Product from Product comparison
2. Verify successful message. Use Assertion.
3. Verify product should remove from comparison. Use Assertion.
4. Verify Product Compare count should be changed from 2 to 1. Use Assertion.

Test-4
1. Click on Grid view on product catalog page
2. Verify all products should come in Grid view. Use Assertion.

Test-5
1. Click on List view on product catalog page
2. Verify all products should come in List view. Use Assertion.

Test-6
1. Click on Sort by - Name( Z-A)
2. Verify all products should arrange from Z-A. Use Assertion.

