package Control;

import Model.AModel;
import Model.User;

import java.util.Observer;

//Abstract controller
public abstract class AController implements Observer {
    protected AModel model;
    protected User user;

    /**
     *Getters and setters to class fields
     */
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setModel(AModel model) {
        this.model = model;
    }

    public AModel getModel() {
        return model;
    }

    //Return rather if there is a user logged in
    public boolean isUserNull(){
        return user == null;
    }
}
