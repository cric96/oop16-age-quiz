package home.controller.observer;

import home.model.building.BuildingType;
/**
 * the world observer.
 */
public interface WorldObserver extends Observer {
    /**
     * tell the observer to go on the next era.
     */
    void nextEra();
    /**
     * tell the observer to increase a level of a specific building.
     * @param building
     *  the building selected
     */
    void nextLevel(BuildingType building);
    /**
     * create a quiz associated with some building.
     * @param building
     *  the building selected
     */
    void createQuiz(BuildingType building);
    /**
     * go back on menu.
     */
    void goOnMenu();
    /**
     * tell the observer that someone wants information of specific building.
     * @param building
     *  the building selected
     */
    void pressOnBuilding(BuildingType building);
    /**
     * tell the observer that someone want information of Kingdom.
     */
    void pressOnKingdom();
}
