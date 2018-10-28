package Control;

import Model.User;
import View.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Controller extends AController {

    //Observe
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof HomePageView) {
            if (arg instanceof String && ((String) arg).equals("Delete user"))
                deleteUser((HomePageView) o);
        } else if (o instanceof MainPageView) {
            if (arg instanceof String[] && ((String[]) arg).length == 2)
                checkLogin((MainPageView) o, (String[]) arg);
        } else if (o instanceof SearchPageView) {
            if (arg instanceof String)
                searchForSimilarUsers((SearchPageView) o, (String) arg);
        } else if (o instanceof SignUp1View) {
            if (arg instanceof Object[] && ((Object[]) arg).length == 4)
                checkSignUp1((SignUp1View) o, (Object[]) arg);
        } else if (o instanceof SignUp2View) {
            if (arg instanceof String[] && ((String[]) arg).length == 3)
                checkSignUp2((SignUp2View) o, (String[]) arg);
        } else if (o instanceof UpdateView) {
            if (arg instanceof Object[] && ((Object[]) arg).length == 6)
                checkUpdate((UpdateView) o, (Object[]) arg);
        }
    }

    //Check if update is valid
    private void checkUpdate(UpdateView updateView, Object[] objects) {
        String username = (String) objects[0];
        String firstname = (String) objects[1];
        String lastname = (String) objects[2];
        String city = (String) objects[3];
        String passwordtxt = (String) objects[4];
        LocalDate birthDate = (LocalDate) objects[5];

        //Check that all details are filled
        if (username == null || firstname == null || lastname == null || city == null || passwordtxt == null || birthDate == null ||
                username.equals("") || firstname.equals("") || lastname.equals("") || city.equals("") || passwordtxt.equals("") || birthDate.toString().equals("")) {
            updateView.setMessageFillDetails();
            return;
        }

        //if username is taken by a different user
        if (!user.getUsername().equals(username) && model.Read(username) != null) {
            updateView.setMessageUsernameTaken();
            return;
        }

        User newUser = new User(username, passwordtxt, birthDate.toString(), firstname, lastname, city);
        model.Update(user.getUsername(), newUser); //update user

        setUser(newUser); //set new user

        updateView.showSuccessAlert(); //Show successful update - executed by view
    }

    //Check if second part of signing up is valid
    private void checkSignUp2(SignUp2View signUp2View, String[] strings) {
        String usernameText = strings[0];
        String passwordText = strings[1];
        String passwordCnfText = strings[2];

        //Make sure al details are filled in
        if(usernameText.equals("") || passwordText.equals("") || passwordCnfText.equals("")){
            signUp2View.setMessageFillDetails();
            return;
        }

        //Check if the username is not taken
        if(model.Read(usernameText) != null) { //user exists
            signUp2View.setMessageUsernameTaken();
            return;
        }

        //Check if passwords match
        if(!passwordText.equals(passwordCnfText)){
            signUp2View.setMessagePasswordsDontMatch();
            return;
        }

        user.setUsername(usernameText);
        user.setPassword(passwordText);
        try {
            model.Create(user); //create user
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        signUp2View.registrationComplete(); //Finish registration
    }

    //Check if first part of signing up is valid
    private void checkSignUp1(SignUp1View signUp1View, Object[] objects) {
        String firstNameText = (String) objects[0];
        String lastNameText = (String) objects[1];
        LocalDate localDate = (LocalDate) objects[2];
        String cityText = (String) objects[3];

        //Make sure every detail is filled in
        if(firstNameText == null || lastNameText == null || localDate == null || cityText == null ||
                firstNameText.equals("") || lastNameText.equals("") || cityText.equals("") || localDate.toString().equals("")){
            signUp1View.setMessageFillDetails();
            return;
        }

        //Check if age of user is legal
        if(!legalAge(localDate)){
            signUp1View.setMessageLegalAge();
            return;
        }

        //Create first part of user
        User user = new User("", "", localDate.toString(), firstNameText, lastNameText, cityText);
        setUser(user);
        signUp1View.successfullySignedUp(); //Go on to second screen (executed by view)
    }

    //Search for similar users - executed by model
    private void searchForSimilarUsers(SearchPageView searchPageView, String username) {
        List<User> users = model.ReadSimilar(username);

        if(users == null || users.size() == 0){ //if there are no results
            //say no results
            searchPageView.showNoResult();
        }
        else {
            //show the users
            searchPageView.showUsers(users);
        }
    }

    //Check rather there is a user logged in
    private void checkLogin(MainPageView mainPageView, String[] str) {
        User currentUser = model.Read(str[0]);

        if (currentUser != null && currentUser.getPassword().equals(str[1])) { //if there is a user logged in
            setUser(currentUser);
            mainPageView.UserExists();
        } else
            mainPageView.UserDoesNotExist();
    }

    //Delete user - executed by model
    private void deleteUser(HomePageView homePageView) {
        model.Delete(user);
        setUser(null); //Set user as null

        homePageView.signOut(); //sign out user
    }

    //Check if user age is legal (above 18)
    private boolean legalAge(LocalDate date) {
        LocalDate dateNow = java.time.LocalDate.now();
        Period p = Period.between(date, dateNow);
        return p.getYears() >= 18;
    }
}
