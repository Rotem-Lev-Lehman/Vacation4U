package View;

import Model.AModel;

public abstract class AController {
    protected AModel model;

    public void setModel(AModel model){
        this.model = model;
    }
}
