package Control;

import Model.*;
import View.*;
import javafx.scene.image.Image;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class Controller extends AController {

    //Observe
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof HomePageView) {
            if (arg instanceof String && ((String) arg).equals("Delete user"))
                deleteUser((HomePageView) o);
            if(arg instanceof String && ((String) arg).equals("Count Unread Messages"))
                countEmails((HomePageView) o);
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
            if (arg instanceof Object[] && ((Object[]) arg).length == 4)
                checkSignUp2((SignUp2View) o, (Object[]) arg);
        } else if (o instanceof UpdateView) {
            if (arg instanceof Object[] && ((Object[]) arg).length == 7)
                checkUpdate((UpdateView) o, (Object[]) arg);
        } else if (o instanceof  CreateVacation){
            if(arg instanceof Vacation)
                createVacation((CreateVacation)o, (Vacation)arg);
        } else if (o instanceof OrderVacation){
            if(arg instanceof PaymentTransaction)
                createPayment((OrderVacation)o,(PaymentTransaction)arg);
        } else if( o instanceof MailBoxView){
            if(arg instanceof String && ((String)arg).equals("Get Messages")){
                searchForMessages((MailBoxView)o);
            }
            else if(arg instanceof Message)
                markMessageAsRead((Message)arg);
        }else if (o instanceof SearchFlight) {
            if (arg instanceof Vacation)
                getResultOfSearch((SearchFlight)o,(Vacation)arg);
        } else if(o instanceof MessageBoxView){
            if(arg instanceof String[] && ((String[])arg).length == 3) {
                String[] strings = (String[]) arg;
                if (strings[1].contains("buyMessage;"))
                    sendApprovedBuyMessage(strings[0], strings[2], strings[1]);
                else if (strings[1].equals("declineBuyMessage"))
                    sendDeclinedBuyMessage(strings[0], strings[2]);
                else if (strings[1].equals("buyApproved"))
                    sendApprovedPaymentMessage(strings[0], strings[2]);
                else if (strings[1].equals("buyDeclined"))
                    sendDeclinedPaymentMessage(strings[0], strings[2]);
                else if (strings[1].equals("OpenPayment"))
                    openPayment((MessageBoxView) o,strings[0], strings[2]);
                else if (strings[1].equals("contactApproved"))
                    sendContactApproved(strings[0], strings[2]);
                else if (strings[1].equals("publisherBuyDeclined"))
                    sendPublisherDeclinedPaymentMessage(strings[0], strings[2]);
                else if (strings[1].equals("vacationBought"))
                    sendVacationBought(strings[0], strings[2]);
            }
            else if(arg instanceof Object[] && ((Object[])arg).length == 2){
                if(((Object[])arg)[0].equals("getVacDetails"))
                    getVacationDetails((MessageBoxView)o,(int)((Object[])arg)[1]);
            }
        }else if(o instanceof MessageTradeBoxView){
            if(arg instanceof String[] && ((String[])arg).length == 4) {
                String[] strings = (String[]) arg;
                if (strings[1].contains("vacationTrade"))
                    sendApprovedTradeMessage(strings[0], strings[2], strings[3]);
                if(strings[1].contains("vacationTradeDeclined"))
                    sendDeclinedTradeMessage(strings[0], strings[2], strings[3]);
            }
            if(arg instanceof Object[] && ((Object[])arg).length == 3) {
                if (((Object[]) arg)[0].equals("getVacDetails"))
                    getVacationsDetails((MessageTradeBoxView) o, (int) ((Object[]) arg)[1], (int) ((Object[]) arg)[2]);
            }

        } else if (o instanceof VacationsView) {
             if (arg instanceof Vacation)
                    setNewOrder((VacationsView) o, (Vacation) arg);
             if(arg instanceof String){
                 if(((String)arg).equals("checkForOwnVacation"))
                     setHasVacation((VacationsView) o);
             }
             if(arg instanceof Object[]){
                 if(((Object[]) arg).length == 2 && ((Object[]) arg)[0].equals("Can Trade"))
                     trade((VacationsView)o, (Vacation)((Object[]) arg)[1]);
             }
        } else if (o instanceof VacationTableView){
            if(arg instanceof Vacation[] && ((Vacation[]) arg).length == 2){
                Vacation[] vacations = (Vacation[])arg;
                sendTradeMessage(vacations[0], vacations[1]);
            }
        }

    }

    private void sendDeclinedTradeMessage(String userNameTo, String vacationIdOwn, String vacationIDToTrade) {
        String messageString = "Hello! User " + user.getUsername() + " accepted your trade";
        User userSendTo = model.ReadUser(userNameTo);
        TradingMessage msg = new TradingMessage(user, userSendTo, messageString,false, Integer.valueOf(vacationIDToTrade), Integer.valueOf(vacationIdOwn));
        model.CreateMessage(msg);

        Vacation v1 = model.ReadVacation(Integer.valueOf(vacationIdOwn));
        Vacation v2 = model.ReadVacation(Integer.valueOf(vacationIDToTrade));

        Order order = new Order(v1, user, OrderStatus.Declined);
        model.UpdateOrder(order);

        Order order2 = new Order(v2, user, OrderStatus.Declined);
        model.UpdateOrder(order2);

    }

    private void sendApprovedTradeMessage(String userNameTo, String vacationIdOwn, String vacationIDToTrade) {
        String messageString = "Hello! User " + user.getUsername() + " accepted your trade";
        User userSendTo = model.ReadUser(userNameTo);
        TradingMessage msg = new TradingMessage(user, userSendTo, messageString,false, Integer.valueOf(vacationIDToTrade), Integer.valueOf(vacationIdOwn));
        model.CreateMessage(msg);

        Vacation v1 = model.ReadVacation(Integer.valueOf(vacationIdOwn));
        Vacation v2 = model.ReadVacation(Integer.valueOf(vacationIDToTrade));

        v1.setAlreadySold(true);
        v2.setAlreadySold(true);
        model.UpdateVacation(v1);
        model.UpdateVacation(v2);

        Order order = new Order(v1, user, OrderStatus.Accepted);
        model.UpdateOrder(order);

        Order order2 = new Order(v2, user, OrderStatus.Accepted);
        model.UpdateOrder(order2);
    }

    private void sendTradeMessage(Vacation vacationToTrade, Vacation vacationOwn) {
        String messageString = "Sorry, User " + user.getUsername() + " didn't approve the trade";
        TradingMessage msg = new TradingMessage(user, vacationToTrade.getSellerId(),messageString, false, vacationToTrade.getVacationID(), vacationOwn.getVacationID());
        model.CreateMessage(msg);

        Order order = new Order(vacationToTrade, user, OrderStatus.WaitingForApproval);
        model.CreateOrder(order);

        Order order2 = new Order(vacationOwn, vacationToTrade.getSellerId(), OrderStatus.WaitingForApproval);
        model.CreateOrder(order2);
    }

    private void trade(VacationsView o, Vacation vacation) {
        if(user.getUsername().equals(vacation.getSellerId().getUsername()))
            o.userIsSeller();
        else {
            List<Vacation> vacations = model.ReadVacationsByUserID(user.getUsername());
            o.openOwnVacations(vacations);
        }
    }

    private void setHasVacation(VacationsView o) {
        if(user != null)
            o.setHasVacations(model.checkHasVacation(user.getUsername()));
    }

    private void sendVacationBought(String sendToUser, String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Yay! You bought the vacation";
        model.CreateMessage(new Message(user,sendTo,messageText,false, Integer.parseInt(vacationID)));

        Vacation v = model.ReadVacation(Integer.parseInt(vacationID));
        v.setAlreadySold(true);
        Order order = new Order(v, user, OrderStatus.Accepted);
        model.UpdateOrder(order);
        model.UpdateVacation(v);
    }

    private void getVacationDetails(MessageBoxView o, int vacationID) {
        Vacation v = model.ReadVacation(vacationID);
        String details = "Origin: " + v.getStartCountry() + "\n" +
                            "Destination: " + v.getDestCountry() + "\n" +
                            "Departure Date: " + v.getStartDate() + "\n" +
                            "Arrival Date: " + v.getEndDate() + "\n" +
                            "Price: " + v.getPrice();
        o.setVacationDeatils(details);
    }

    private void getVacationsDetails(MessageTradeBoxView o, int vacationIDOwn, int vacationIDToTrade){
        Vacation vOwn = model.ReadVacation(vacationIDOwn);
        String detailsOwn = "Origin: " + vOwn.getStartCountry() + "\n" +
                "Destination: " + vOwn.getDestCountry() + "\n" +
                "Departure Date: " + vOwn.getStartDate() + "\n" +
                "Arrival Date: " + vOwn.getEndDate() + "\n" +
                "Price: " + vOwn.getPrice();

        Vacation vToTrade = model.ReadVacation(vacationIDToTrade);
        String detailsToTrade = "Origin: " + vToTrade.getStartCountry() + "\n" +
                "Destination: " + vToTrade.getDestCountry() + "\n" +
                "Departure Date: " + vToTrade.getStartDate() + "\n" +
                "Arrival Date: " + vToTrade.getEndDate() + "\n" +
                "Price: " + vToTrade.getPrice();

        o.setVacationDetails(detailsOwn, detailsToTrade);
    }

    //Message from publisher (actually from buyer) to buyer - approve money delivery
    private void sendContactApproved(String sendToUser, String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Hey! Please confirm that you have payed for the vacation";
        model.CreateMessage(new Message(sendTo, user, messageText, false, Integer.parseInt(vacationID)));

    }

    private void openPayment(MessageBoxView msgBoxView, String sendToUser, String vacationID) {
        Vacation v = model.ReadVacation(Integer.parseInt(vacationID));
        Order order = model.ReadOrder(v, user);
        msgBoxView.moveToPayment(order);

    }

    //from buyer to publisher when the buyer didn't pay
    private void sendDeclinedPaymentMessage(String sendToUser, String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Sorry, User " + user.getUsername() + " didn't pay for the vacation";
        model.CreateMessage(new Message(user, sendTo, messageText, false, Integer.parseInt(vacationID)));

        //updae order status
        Vacation v = new Vacation(user,null,"","","","","","",-1,"",false,-1,-1,-1,-1);
        v.setVacationID(Integer.parseInt(vacationID));
        Order order = new Order(v, user, OrderStatus.Declined);
        model.UpdateOrder(order);
    }

    //from publisher to buyer when the publisher claims the buyer didn't pay
    private void sendPublisherDeclinedPaymentMessage(String sendToUser, String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Sorry, User " + user.getUsername() + " claim you didn't pay for the vacation. The order is cancelled";
        model.CreateMessage(new Message(user, sendTo, messageText, false, Integer.parseInt(vacationID)));

        //updae order status
        Vacation v = new Vacation(user,null,"","","","","","",-1,"",false,-1,-1,-1,-1);
        v.setVacationID(Integer.parseInt(vacationID));
        Order order = new Order(v, user, OrderStatus.Declined);
        model.UpdateOrder(order);
    }

    /*//From buyer to publisher - Vacation was bought
    private void sendApprovedPaymentMessage(String sendToUser,String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Yay! User " + user.getUsername() + " bought the vacation you have published";
        model.CreateMessage(new Message(user,sendTo,messageText,false, Integer.parseInt(vacationID)));

        Vacation v = model.ReadVacation(Integer.parseInt(vacationID));
        v.setAlreadySold(true);
        Order order = new Order(v, user, OrderStatus.Accepted);
        model.UpdateOrder(order);
        model.UpdateVacation(v);
    }*/

    //From buyer to publisher - buyer paid, does publisher agree?
    private void sendApprovedPaymentMessage(String sendToUser,String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Hey! Please confirm that user " + user.getUsername() +" have payed for the vacation";
        model.CreateMessage(new Message(user, sendTo, messageText, false, Integer.parseInt(vacationID)));
    }

    //From publisher to buyer when the publisher didn't approve the deal
    private void sendDeclinedBuyMessage(String sendToUser, String vacationID) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String messageText = "Sorry, User " + user.getUsername() + " didn't approve your order request";
        model.CreateMessage(new Message(user, sendTo, messageText, false, Integer.parseInt(vacationID)));

        //updae order status
        Vacation v = new Vacation(user,null,"","","","","","",-1,"",false,-1,-1,-1,-1);
        v.setVacationID(Integer.parseInt(vacationID));
        User buyer = new User(sendToUser,"","","","","");
        Order order = new Order(v, buyer, OrderStatus.Declined);
        model.UpdateOrder(order);
    }

    //From publisher to buyer when the publisher approves the request
    private void sendApprovedBuyMessage(String sendToUser, String vacationID, String partPhoneNumber) {
        User sendTo = new User(sendToUser, "1","1","1","1","1");
        String phoneNumber = partPhoneNumber.split(";")[1];
        String messageText = "Hello! User " + user.getUsername() + " approved your order request. Contact Info: " + phoneNumber;
        model.CreateMessage(new Message(user, sendTo, messageText, false, Integer.parseInt(vacationID)));

        //update order status
        Vacation v = new Vacation(user,null,"","","","","","",-1,"",false,-1,-1,-1,-1);
        v.setVacationID(Integer.parseInt(vacationID));
        User buyer = new User(sendToUser,"","","","","");
        Order order = new Order(v, buyer, OrderStatus.WaitingForPayment);
        model.UpdateOrder(order);
    }

    private void markMessageAsRead(Message message) {
        model.UpdateMessageAsSeen(message);
    }

    private void searchForMessages(MailBoxView o) {
        List<Message> messages = model.ReadAllMessages(user.getUsername());

        if(messages== null || messages.size() == 0){ //if there are no results
            //say no results
            o.showNoResult();
        }
        else {
            //show messages
           o.showMessages(messages);
        }
    }

    private void createPayment(OrderVacation o, PaymentTransaction arg) {

        model.CreatePaymentTransaction(arg);
    }

    private void createVacation(CreateVacation o, Vacation arg) {
        model.CreateVacation(arg);
    }

    private void countEmails(HomePageView o) {
        int count = model.CountUnseenMessages(user.getUsername());
        o.updateUnreadEmailsCount(count);
    }

    //Check if update is valid
    private void checkUpdate(UpdateView updateView, Object[] objects) {
        String username = (String) objects[0];
        String firstname = (String) objects[1];
        String lastname = (String) objects[2];
        String city = (String) objects[3];
        String passwordtxt = (String) objects[4];
        LocalDate birthDate = (LocalDate) objects[5];
        File imageFile = (File) objects[6];

        //Check that all details are filled
        if (username == null || firstname == null || lastname == null || city == null || passwordtxt == null || birthDate == null ||
                username.equals("") || firstname.equals("") || lastname.equals("") || city.equals("") || passwordtxt.equals("") || birthDate.toString().equals("")) {
            updateView.setMessageFillDetails();
            return;
        }

        //if username is taken by a different user
        if (!user.getUsername().equals(username) && model.ReadUser(username) != null) {
            updateView.setMessageUsernameTaken();
            return;
        }

        //Check if age of user is legal
        if(!legalAge(birthDate)){
            updateView.setMessageLegalAge();
            return;
        }

        User newUser = new User(username, passwordtxt, birthDate.toString(), firstname, lastname, city);
        model.UpdateUser(user.getUsername(), newUser); //update user
        if(imageFile != null)
            model.UpdateUsersProfileImage(user.getUsername(), imageFile);

        setUser(newUser); //set new user

        updateView.showSuccessAlert(); //Show successful update - executed by view
    }

    //Check if second part of signing up is valid
    private void checkSignUp2(SignUp2View signUp2View, Object[] strings) {
        String usernameText = (String)strings[0];
        String passwordText = (String)strings[1];
        String passwordCnfText = (String)strings[2];
        File fileImage = (File)strings[3];

        //Make sure al details are filled in
        if(usernameText.equals("") || passwordText.equals("") || passwordCnfText.equals("")){
            signUp2View.setMessageFillDetails();
            return;
        }

        //Check if the username is not taken
        if(model.ReadUser(usernameText) != null) { //user exists
            signUp2View.setMessageUsernameTaken();
            return;
        }

        //Check if passwords match
        if(!passwordText.equals(passwordCnfText)){
            signUp2View.setMessagePasswordsDontMatch();
            return;
        }

        //check if Image was set
        if(fileImage != null)
            user.setProfileImage(new Image(fileImage.toURI().toString()));

        user.setUsername(usernameText);
        user.setPassword(passwordText);
        try {
            model.CreateUser(user); //create user
            if(fileImage!= null)
                model.CreateUsersProfileImage(user.getUsername(), fileImage);
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
        List<User> users = model.ReadSimilarUsers(username);

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
        User currentUser = model.ReadUser(str[0]);

        if (currentUser != null && currentUser.getPassword().equals(str[1])) { //if there is a user logged in
            setUser(currentUser);
            currentUser.setProfileImage(model.ReadUsersProfileImage(str[0]));
            mainPageView.UserExists();
        } else
            mainPageView.UserDoesNotExist();
    }

    //Delete user - executed by model
    private void deleteUser(HomePageView homePageView) {
        model.DeleteUser(user);
        setUser(null); //Set user as null

        homePageView.signOut(); //sign out user
    }

    //Check if user age is legal (above 18)
    private boolean legalAge(LocalDate date) {
        LocalDate dateNow = java.time.LocalDate.now();
        Period p = Period.between(date, dateNow);
        return p.getYears() >= 18;
    }
    private void setNewOrder(VacationsView vacationView, Vacation v) {

        if(user != null && !user.getUsername().equals(v.getSellerId().getUsername())) {
            model.CreateOrder(new Order(v,user,OrderStatus.WaitingForApproval));
            String text = "Hey! User " + user.getUsername() + " wants to buy your vacation";
            //message from buyer to publisher request to buy vacation
            model.CreateMessage(new Message(user, v.getSellerId(),text,false,v.getVacationID()));
            vacationView.userExist();
        }
        else if(user != null && user.getUsername().equals(v.getSellerId().getUsername())){
            vacationView.userIsSeller();
        }
         else
             vacationView.userNotExist();

    }



    private void getResultOfSearch(SearchFlight searchFlight, Vacation v){
        List<Vacation> vacations = model.ReadSimilarVacations(v, new Comparator<Vacation>() {
            @Override
            public int compare(Vacation o1, Vacation o2) {
                if(o1.getPrice()>o2.getPrice())
                    return 1;
                else if(o1.getPrice()<o2.getPrice())
                    return -1;
                return 0;

            }
        });

        if(vacations.size() == 0)
            searchFlight.showEmptyList();
        else
            searchFlight.show(vacations);
     //   VacationsView.show(vacations);


    }

    /*private void getVacationToShow(VacationsView vacationView, Vacation v){
            List<Vacation> vacations = model.ReadSimilarVacations(v, new Comparator<Vacation>() {
                @Override
                public int compare(Vacation o1, Vacation o2) {
                    if(o1.getPrice()>o2.getPrice())
                        return 1;
                    else if(o1.getPrice()<o2.getPrice())
                        return -1;
                    return 0;

                }
            });
            vacationView.vacationToShow(vacations,0);


        }*/


}
