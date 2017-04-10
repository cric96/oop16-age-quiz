package home.controller;

import java.util.Set;

import home.view.View;
public interface Controller {
    Set<View<?>> getViews();
    /**
     * when the controller is switched check if the model
     * is changed and notify to his own views.
     */
    void checkUpdate();
}
