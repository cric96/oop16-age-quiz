package home.view;

import home.controller.Controller;

public interface View <E extends Controller>{
    void attachOn(E controller);
    //showMessage(Message error)
}
