package Control;

import Model.User;
import View.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Observable;

public class Controller extends AController {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof HomePageView) {
            if (arg instanceof String && ((String) arg).equals("Delete user"))
                deleteUser((HomePageView) o);
        } else if (o instanceof MainPageView) {
            if (arg instanceof String[] && ((String[]) arg).length == 2 && ((String[]) arg)[0] != null && ((String[]) arg)[1] != null)
                checkLogin((MainPageView) o, (String[]) arg);
        } else if (o instanceof SearchPageView) {
            if (arg instanceof String)
                searchForSimilarUsers((SearchPageView) o, (String) arg);
        } else if (o instanceof SignUp1View) {
            if (arg instanceof Object[] && ((Object[]) arg).length == 4 && ((Object[]) arg)[0] instanceof String && ((Object[]) arg)[1] instanceof String && ((Object[]) arg)[2] instanceof LocalDate && ((Object[]) arg)[3] instanceof String)
                checkSignUp1((SignUp1View) o, (Object[]) arg);
        } else if (o instanceof SignUp2View) {
            if (arg instanceof String[] && ((String[]) arg).length == 3 && ((String[]) arg)[0] != null && ((String[]) arg)[1] != null && ((String[]) arg)[2] != null)
                checkSignUp2((SignUp2View) o, (String[]) arg);
        } else if (o instanceof UpdateView) {
            if (arg instanceof String[] && ((String[]) arg).length == 5 && ((String[]) arg)[0] != null && ((String[]) arg)[1] != null && ((String[]) arg)[2] != null && ((String[]) arg)[3] != null && ((String[]) arg)[4] != null)
                checkUpdate((UpdateView) o, (String[]) arg);
        }
    }

    private void checkUpdate(UpdateView updateView, String[] strings) {
        String username = strings[0];
        String firstname = strings[1];
        String lastname = strings[2];
        String city = strings[3];
        String passwordtxt = strings[4];

        if(username.equals("") || firstname.equals("") || lastname.equals("") || city.equals("") || passwordtxt.equals("")){
            updateView.setMessageFillDetails();
            return;
        }
        if(!user.getUsername().equals(username) && model.Read(username) != null){ //if username is taken by a different user
            updateView.setMessageUsernameTaken();
            return;
        }

        User newUser =new User(username, passwordtxt , user.getBirthdate() ,firstname, lastname, city);
        model.Update(user.getUsername(), newUser);

        setUser(newUser);

        updateView.showSuccessAlert();
    }

    private void checkSignUp2(SignUp2View signUp2View, String[] strings) {
        String usernameText = strings[0];
        String passwordText = strings[1];
        String passwordCnfText = strings[2];

        if(usernameText.equals("") || passwordText.equals("") || passwordCnfText.equals("")){
            signUp2View.setMessageFillDetails();
            return;
        }

        if(model.Read(usernameText) != null) { //user exists
            signUp2View.setMessageUsernameTaken();
            return;
        }

        if(!passwordText.equals(passwordCnfText)){
            signUp2View.setMessagePasswordsDontMatch();
            return;
        }

        user.setUsername(usernameText);
        user.setPassword(passwordText);
        try {
            model.Create(user);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        signUp2View.registrationComplete();
    }

    private void checkSignUp1(SignUp1View signUp1View, Object[] objects) {
        String firstNameText = (String) objects[0];
        String lastNameText = (String) objects[1];
        LocalDate localDate = (LocalDate) objects[2];
        String cityText = (String) objects[3];

        if(firstNameText == null || lastNameText == null || localDate == null || cityText == null ||
                firstNameText.equals("") || lastNameText.equals("") || cityText.equals("") || localDate.toString().equals("")){
            signUp1View.setMessageFillDetails();
            return;
        }

        if(!legalAge(localDate)){
            signUp1View.setMessageLegalAge();
            return;
        }

        User user = new User("", "", localDate.toString(), firstNameText, lastNameText, cityText);
        setUser(user);
        signUp1View.successfullySignedUp();
    }

    private void searchForSimilarUsers(SearchPageView searchPageView, String username) {
        List<User> users = model.ReadSimilar(username);

        if(users == null || users.size() == 0){
            //say no results
            searchPageView.showNoResult();
        }
        else {
            //show the users
            searchPageView.showUsers(users);
        }
    }

    private void checkLogin(MainPageView mainPageView, String[] str) {
        User currentUser = model.Read(str[0]);

        if (currentUser != null && currentUser.getPassword().equals(str[1])) {
            setUser(currentUser);
            mainPageView.UserExists();
        } else
            mainPageView.UserDoesNotExist();
    }

    private void deleteUser(HomePageView homePageView) {
        model.Delete(user);
        setUser(null);

        homePageView.signOut();
    }

    private boolean legalAge(LocalDate date) {
        LocalDate dateNow = java.time.LocalDate.now();
        Period p = Period.between(date, dateNow);
        return p.getYears() >= 18;
    }
}
