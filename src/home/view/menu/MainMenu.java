package home.view.menu;

import java.util.List;

/**
 *  a interface to define logic of a general main menu model. 
 */
public interface MainMenu {
    /**
     * 
     * @return a set that represent the text of buttons in the main menu
     */
    List<String> buttonsNameList();

    /**
     * 
     * @return the path of background menu image
     */
    String backgroundPath();

    /**
     * 
     * @return the title of menu (the game's name)
     */
    String getTitle();
}
