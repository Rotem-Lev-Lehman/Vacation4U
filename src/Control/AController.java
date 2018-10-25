package Control;

import Model.AModel;
import Model.User;

import java.util.Observer;

public abstract class AController implements Observer {
    protected AModel model;
    protected User user;

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

    public boolean isUserNull(){
        return user == null;
    }
}
