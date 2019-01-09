# EveryVacation4U
A platform for air plane tickets deals

=====Welcome to EveryVacation4U===============

If you're having any trouble with our app please read our instructions:
- Please do import Maven settings. When cloning the project you will be asked to import maven settings, please make sure you do (maybe your IDE imports them automatically).
- Specify your SDK as Java 8 (JDK 1.8).
- To run the project, go to "Main" file under the "src/main/java/View" and run it.
- To create user - click "not registered yet" and register your user. If you keep getting "must be above 18" or "please fill all details" error, try to click enter after entering your birthdate.
- To log in - fill in username and password and click login.
- To edit profile - click on the gear image after logging in and change your details to what you like to.
- To sign out - click on the small door on the right corner.
- To delete user - click "Delete My User" after logging in, you will be asked to confirm your decision and after you click "ok" - your user will be deleted.
- If you don't want to create a user you are welcome to use our user. Username: admin, Password: admin (Very original right?)
- You can search for vacation even if you're signed in by clicking "Search for Vacations and Flights".
- You can search for vacation after signing in too.

---Creating Vacation---
- You can create vacation by clicking create vacation - please do make sure you fill all fields!
- If you want to add a place to sleep in your vacation, simply click on the "Place to sleep" button and add the details
- To create the vacation click "Create Vacation"

---Searching Vacation----
- To Search vacation click the "search vacations" button
- You can search by zero or more parameters
- After you click search you'll see a windows with the results. Travel the results with the "Next" and "Previous" Button
- If you want to order the vacation, simply click "order" - make sure you're signed in and that you're not the same user who published the vacation
- After you click the order button, A request will be send to the seller

---Mail Box---
- To view messages click on the email logo in the right corner
- If there are new messages it will appear with a red mark
- To view full message click on it in the table view

---Purchasing Process----
- User 1 creates vacation
- User 2 orders vacation
- User 1 will get a message that someone wants to buy the vacation and will choose rather to accept or decline the request
- If User 1 chose to accept the order, he will need to input his phone number
- User 2 will get a message according to user 1's choice. If user 1 accepted the request then user 2 will get another message asking him to approve that he has paid to User 1
- If User 2 confirms that he has paid, then User 1 will get a message that will ask him to confirm that User 2 actually paid. If User 2 doesn't confirm that he has paid then the purchase is canceled.
- If User 1 confirms that User 2 has paid, then the purchase is confirmed in the system. If User 1 doesn't confirm that User 2 has paid then the purchase is canceled.
- All the processes above are documented in that database

---Trading Process---
- User 1 creates vacation
- User 2 creates vacation
- User 2 trades choose the "Trade" button after he searched User 1's vacation
- User 2 chooses one of his own vacation that he wants to trade with. A message is sent to User 1 regarding the trade
- If user 1 confirms the trade, then User 2 will get a message saying that the trade has been confirmed. If User 1 doesn't accept the trade, then User 2 gets a message saying the trade is off.
- All the processes above are documented in that database
