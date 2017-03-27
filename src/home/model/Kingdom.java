package home.model;

import home.model.composite.Composite;


//TODO CONCLUDI TUTTA QUESTA INTERFACCIA
/**
 * define the interface of a kingdom.
 */

public interface Kingdom extends Composite {
    /**
     * 
     * @return
     *  the current age of the kingdom
     */
    String getAgeName();
    
    int getExperienceAmount();
    
    void addExperience(int amount);
    
    void decExperiene(int amount);
}
