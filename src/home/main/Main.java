package home.main;

import home.view.App;
import javafx.application.Application;

/**
 * This class is used to start the videogame
 * and to set some values.
 * 
 */
public final class Main {
    private Main() { }
    /** 
     * @param args
     *          not used in this contest 
     */
    public static void main(final String[] args) {
        Application.launch(App.class);
    }
}
