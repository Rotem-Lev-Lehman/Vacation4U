package Model;

import Model.DataBaseCommunication.DataBaseManager;

//Projects Model - holds the database manager
public class Model extends AModel{

    public Model() {
        dataBaseManager = new DataBaseManager();
    }


}
