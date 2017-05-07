package home.main;

import home.view.App;
import home.view.debug.DebugApplication;
import javafx.application.Application;

/**
 * This class is used to start the video game
 * and to set some values.
 * 
 */
public final class Main {
    private static final boolean DEBUG = false;
    private Main() { }
    /** 
     * @param args
     *          not used in this contest 
     */
    public static void main(final String[] args) {
        if (DEBUG) {
            DebugApplication.launch();
        } else {
            Application.launch(App.class);
        }
    }
}
