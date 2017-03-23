package home.model;

import home.model.building.Building;
import home.model.building.BuildingType;
import home.model.status.StatusName;
import home.model.utility.Pair;

//TODO CONCLUDI TUTTA QUESTA INTERFACCIA
/**
 * define the interface of a kingdom.
 */

public interface Kingdom {
    /**
     * 
     * @return
     *  the current age of the kingdom
     */
    String getAgeName();
    
    int getExperienceNecessary();
    
    int getExperiece();
    
    boolean ageUp();
    
    boolean buildingLevelUp(BuildingType name);
    
    Building getBuilding(Building name);
    
    Pair<StatusName,Integer> getStatusInfo();
    
    void changeStatus(StatusName name,int amount);
    
    void addExperience(int experienceAmount);
    
}
