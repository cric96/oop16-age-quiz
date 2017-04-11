package home.controller;

public interface MenuController extends Controller {
    
    void newGamePressed();
    
    void createGame(String name, Profile profile);
    
    void loadGamePressed();
    
    void loadGame(Profile profile);
    
    void exitPressed();
}
